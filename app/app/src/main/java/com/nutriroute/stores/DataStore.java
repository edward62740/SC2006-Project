import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.models.GenericUser;

public class FirebaseDataStore implements IDataStore {

    private final DatabaseReference databaseReference;
    private final Gson gson;

    public FirebaseDataStore() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference("users");
        this.gson = new Gson();
    }

    public boolean addUser(GenericUser<?> user) {
        try {
            String userId = user.getId().toString(); // Assuming GenericUser has a getId() method
            String json = gson.toJson(user);

            databaseReference.child(userId).setValue(json);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
