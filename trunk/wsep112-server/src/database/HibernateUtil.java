package database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import domain.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import common.network.messages.ErrorMessage;

import domain.Forum;
import domain.Thread;
import domain.User;

public class HibernateUtil {
		
		
	private static org.hibernate.SessionFactory sessionFactory;
	 
	  public static SessionFactory getSessionFactory() {
	    if (sessionFactory == null) {
	      initSessionFactory();
	    }
	    return sessionFactory;
	  }
	 
	  private static synchronized void initSessionFactory() {
	    sessionFactory = new Configuration().configure().buildSessionFactory();
	  }
	 
	  public static Session getSession() {
	    return getSessionFactory().openSession();
	  }
	  
	  /**
	   * Update existing object in the database.
	   * @param obj
	   */
	   public static void updateDB(Object obj){
		   try{
			   Session session = getSession();
			   Transaction transaction = session.beginTransaction();
			   session.merge(obj);
			   transaction.commit();
			   session.flush();
			   session.close();
			  
		   }
		   catch(Exception e){
			   e.printStackTrace();
			  
		   }
	   }
	   
	   /**
	    * Insert new object to the database.
	    * @param obj
	    * @return
	    */
	   public static Serializable insertDB(Object obj){
		   try{
			   Session session = getSession();
			   Transaction transaction = session.beginTransaction();
			   Serializable ans = session.save(obj);
			   transaction.commit();
			   session.flush();
			   session.close();
			   return ans;
		   }
		   catch(Exception e){
			   e.printStackTrace();
			   return null;
		   }
	   }
	   
	   public static User retrieveUser(String username){
	try{  
		   Session session = getSession();
		   Transaction transaction = session.beginTransaction();
		   Query q = session.createQuery("from User as u where u._username = :username");
		   q.setParameter("username", username);
		   List<User> tUserList= q.list();
		   session.close();
		   
		   if (tUserList.size() == 0)
			   	return null;
		   else return tUserList.get(0);
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
		
	   }
	   
	   public static List<User> retrieveOnlineUsers(){
		 try{  Session session = getSession();
		   Transaction transaction = session.beginTransaction();
		   Query q = session.createQuery("from User as u where u._status= :status");
		   q.setParameter("status", "ONLINE");
		   List<User> tAns = q.list();
		   
		   return tAns;
		   
		 }catch (Exception e){
			 e.printStackTrace();
			 return null;
			 
		 }
	   }
	   
	   public static Forum retrieveForum(int id){
		   try{Session session = getSession();
		   Transaction transaction = session.beginTransaction();
		   Query q = session.createQuery("from Forum as f where f._forumId= :id");
		   q.setParameter("id", id);
		   List<Forum> tAns = q.list();
		   
		   if (tAns.size() == 0)
			   	return null;
		   else return tAns.get(0);
		   
		  
		   }catch (Exception e){
			   e.printStackTrace();
		   }
		  return null;
	  }
	   
	   public static List<Forum> retrieveForumsList(){
		  try{ Session session = getSession();
		   Transaction transaction = session.beginTransaction();
		   return session.createQuery("from Forum").list();
		 
		  }catch (Exception e){
			  e.printStackTrace();
			  return null;
		  }
	  }
	   
	   public static List<Thread> retrieveThreadList(int ForumId){
		   try{Session session = getSession();
		   Transaction transaction = session.beginTransaction();
		   Query q = session.createQuery("from Thread as t where t._forumId= :id");
		   q.setParameter("id", ForumId);
		   List<Thread> tAns = q.list();
		   
		   return tAns;
		   
		   }catch(Exception e){
			   e.printStackTrace();
			   return null;
		   }
		   
	  }
	   
	   public static List<Post> retrievePostList(int threadId){
		   
		   try{
			   
			   Session session = getSession();
			   Transaction transaction = session.beginTransaction();

			   Query q = session.createQuery("from Post as p where p._threadID= :id");
			   q.setParameter("id", threadId);
			   List<Post> tAns = q.list();
			   
			   return tAns;
		
		   }
		   catch (Exception e){
			   
			   e.printStackTrace();
			   return null;
		   }
	  }

	public static Thread retrieveThread(int threadId) {
		
		//			can we change the db query to receive only one thread?
		try{
			   
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			
			  Query q = session.createQuery("from Thread as t where t._threadID= :id");
			  q.setParameter("id", threadId);
			  List<Thread> tAns = q.list();
			  
			  if (tAns.size() == 0)
			   	return null;
			  else return tAns.get(0);
		}
		catch(Exception e){
			   
			e.printStackTrace();
			return null;
		}
	}

	// SHIRAN - STOP HERE 
	
	public static List<Thread> retrieveAllThreadsList() {
		
		try{
			   
			Session session = getSession();
			Transaction transaction = session.beginTransaction();

			//return session.createQuery("from Thread where forum_id="+ForumId).list();
		   
			List<Thread> tThreadList = session.createQuery("from Thread").list();
		
			return tThreadList;
		}
		catch(Exception e){
			   
			e.printStackTrace();
			return null;
		}
	}
	
	public static User retrievePostOwner(int postId){
		try{
			   
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			
		
			List<Post> tPostList = session.createQuery("from Post").list();
		
			for (int i=0 ; i<tPostList.size();++i){
				Post tCurrPost = tPostList.get(i);
				if (tCurrPost.get_post_id() == postId){
					return tCurrPost.get_Owner();
				}
					
			}
			return null;
		}
		catch(Exception e){
			   
			e.printStackTrace();
			return null;
		}
	}
	
	public static void runQuery(String str){
		try{
			   
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			
			Query q = session.createSQLQuery(str);
			q.executeUpdate();
			session.close();
			
		}
		catch(Exception e){
			   
			e.printStackTrace();
		
		}
	}
	
	public static int deletePost(int postId){
		try{
			   
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			
		//	session.delete(obj);
			Query q = session.createQuery("delete from Post as p where p._post_id = :postId");
			q.setParameter("postId", postId);
			int row_count = q.executeUpdate();
			session.close();
			
			return row_count;
			
		}
		catch(Exception e){
			   
			e.printStackTrace();
			return 0;
		
		}
	}
	
	public static int deleteThread(int threadId){
		try{
			   
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			
			Query q = session.createQuery("delete from Thread as t where t._threadID = :threadId");
			q.setParameter("threadId", threadId);
			int row_count = q.executeUpdate();
			session.close();
			
			return row_count;
			
		}
		catch(Exception e){
			   
			e.printStackTrace();
			return 0;
		
		}
	}
	
	
	public static List<Post> retrieveUserPosts(String username){
		User tUser = retrieveUser(username);
		try{
			   
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			
			Query q = session.createQuery("from Post as p where p._owner._username = :_username");
			q.setParameter("_username", username);
			List<Post> tPostList = q.list();
			
			session.close();
			
			return tPostList;
			
			
		}
		catch(Exception e){
			   
			e.printStackTrace();
			return null;
		}
		
	}
}
