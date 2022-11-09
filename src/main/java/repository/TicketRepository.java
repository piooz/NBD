package repository;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.TransactionBody;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import model.NormalMdb;
import model.ShowMdb;
import model.TicketMdb;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class TicketRepository extends Repository{

    private final MongoCollection<TicketMdb> ticketCollection;
    private final MongoCollection<ShowMdb> showCollection;

    public TicketRepository() {
        initConnection();
//        getCinemaDB().createCollection("tickets");
        ticketCollection = getCinemaDB().getCollection("tickets", TicketMdb.class);
        showCollection = getCinemaDB().getCollection("shows", ShowMdb.class);
    }

    public boolean add(TicketMdb ticket) {
        try (ClientSession session = getMongoClient().startSession()) {
            session.startTransaction();
            transactionBody(ticket);
            session.commitTransaction();
        } catch (RuntimeException e) {
            return false;
        }
        return true;
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
        Bson update = Updates.inc("availableSeats", 1);
        showCollection.findOneAndUpdate(fil, update);
        ticketCollection.insertOne(ticket);
    }

    private boolean isExisting(TicketMdb ticket) {
        ArrayList<TicketMdb> ls = find(ticket.getId());
        return !ls.isEmpty();
    }


    public TicketMdb remove(ObjectId id) {
        Bson filer = eq("_id", id);
        return ticketCollection.findOneAndDelete(filer);
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
