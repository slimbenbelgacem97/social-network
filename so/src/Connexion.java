import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

import static org.neo4j.driver.Values.parameters;

public class Connexion implements AutoCloseable
{
    public final Driver driver;
    private Session session;

    public Connexion( String url, String user, String password ){
        driver = GraphDatabase.driver( url, AuthTokens.basic( user, password ) );
    }
    public void CreateSession(){
    	session = driver.session();
    }
    

    @Override
    public void close() throws Exception{
        driver.close();
    }

    public void executeSet(String cypher)
    {
        try ( Session session = driver.session() ){
            session.writeTransaction(tx -> {
                Result result = tx.run(cypher);
                System.out.println("Task succeeded!");
                return "Task succeeded!";
            });
        }catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
    
    
    
    
    public Result executeGet( final String cypher ){
    
        try {
        	return session.run( cypher );
    
        	
	
        }catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
    }
}