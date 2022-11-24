package repository;

import com.mongodb.MongoCommandException;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.ValidationOptions;
import com.mongodb.client.result.UpdateResult;
import model.NormalMdb;
import model.ShowMdb;
import model.TicketMdb;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class TicketRepository extends Repository<TicketMdb>{

    private final MongoCollection<TicketMdb> ticketCollection;
    private final MongoCollection<ShowMdb> showCollection;

    public TicketRepository() {
        initConnection();
        try {
            getCinemaDB().createCollection("tickets", new CreateCollectionOptions().validationOptions( new ValidationOptions().validator(
                    Document.parse(
                            """
    {
       jsonSchema: {
          bsonType: "object",
          properties: {
            show: {
              bsonType: "object",
              properties: {
                show: {
                    bsonType: "object",
                    properties: {
                      movie: {
                      bsonType: "object",
                      description: "movie object"
                    },
                   seats: {
                      bsonType: "int",
                      minimum: 1,
                      description: "must be a positive integer"
                   },
                   availableSeats: {
                      bsonType: "int",
                      minimum: 1,
                      description: "must be a positive integer"
                   },
                   hallNumber: {
                      bsonType: "int",
                      minimum: 0,
                      description: "must be a integer"
                   }
                  }
                }
              }
            },
            client: {
              bsonType: "object",
              required: [ "email" ],
              properties: {
                 lastName: {
                    bsonType: "string",
                    description: "must be a string"
                 },
                 email: {
                    bsonType: "string",
                    description: "must be a string"
                 }
              }
            },
             seatNumber: {
                bsonType: "int",
                minimum: 0,
                description: "must be a positive integer"
             },
             price: {
                bsonType: "float",
                minimum: 0,
                description: "must be a positive float"
             }
          }
       }
    }
                    """
                    )
            )));
        } catch(MongoCommandException ignored) {
        }

        ticketCollection = getCinemaDB().getCollection("tickets", TicketMdb.class);
        showCollection = getCinemaDB().getCollection("shows", ShowMdb.class);
    }

    @Override
    public TicketMdb get(ObjectId id) {
        ArrayList<TicketMdb> list = find(id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public boolean add(TicketMdb ticket) {

        ticketCollection.insertOne(ticket);
        return true;

    }
    public boolean add2(TicketMdb ticket) {
        try (ClientSession session = getMongoClient().startSession()) {
            session.startTransaction();
            transactionBody(ticket);
            session.commitTransaction();
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    @Override
    public void update(TicketMdb item) {
        Bson filter = eq("_id", item.getId());
        ticketCollection.findOneAndReplace(filter, item);
    }

    private void transactionBody(TicketMdb ticket) {
        ShowMdb dbShow = getShowFromDatabase(ticket);
        if (dbShow == null)
        {
            throw new RuntimeException();
        }

        if(isExisting(ticket) || dbShow.getAvailableSeats() == 0) {
            throw new RuntimeException();
        }
        updateDataBase(ticket, dbShow);
    }


    private ShowMdb getShowFromDatabase(TicketMdb ticket) {
        Bson filter = eq("_id", ticket.getShow());
        ArrayList<ShowMdb> ls = showCollection.find(filter, ShowMdb.class).into(new ArrayList<> ());
        ShowMdb sh;
        if(!ls.isEmpty())
        {
             sh = ls.get(0);
             return sh;
        }
        return null;
    }

    private void updateDataBase(TicketMdb ticket, ShowMdb show) {
        Bson fil = eq("_id", show.getId());
        Bson update = Updates.inc("availableSeats", -1);
        showCollection.findOneAndUpdate(fil, update);
        ticketCollection.insertOne(ticket);
    }

    private boolean isExisting(TicketMdb ticket) {
        ArrayList<TicketMdb> ls = find(ticket.getId());
        return !ls.isEmpty();
    }


    @Override
    public TicketMdb remove(ObjectId id) {
        TicketMdb ticket;
        Bson ticketFiler = eq("_id", id);
        ClientSession session = getMongoClient().startSession();

        session.startTransaction();
            ticket = ticketCollection.findOneAndDelete(ticketFiler);
            Bson update = Updates.inc("availableSeats", 1);
        assert ticket != null;
        Bson showFilter = eq("_id", ticket.getShow());
            showCollection.updateOne(showFilter, update);
        session.commitTransaction();

        return ticket;
    }
    public void drop()
    {
        ticketCollection.drop();
    }

    public ArrayList<TicketMdb> findAll() {
        return ticketCollection.find().into(new ArrayList<> ());
    }

    public ArrayList<TicketMdb> find(ObjectId id) {
        Bson filter = eq("_id", id);

        ArrayList<TicketMdb> ls = ticketCollection.find(filter, NormalMdb.class).into(new ArrayList<> ());
        return ls;
    }

    public TicketMdb updateOne(ObjectId id, Bson updateOperation) {
        Bson filter = eq("_id", id);
        return ticketCollection.findOneAndUpdate(filter, updateOperation);
    }

    public UpdateResult updateMany(Bson filter, Bson updateOperation) {
        return ticketCollection.updateMany(filter, updateOperation);
    }
}
