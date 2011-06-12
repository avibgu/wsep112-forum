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
	   public synchronized static void updateDB(Object obj){
		   Session session=null;
		   Transaction transaction = null;
		   try{
			   session = getSession();
			   transaction = session.beginTransaction();
			   session.merge(obj);
			   transaction.commit();
			   session.flush();
			   session.close();
			  
		   }
		   catch(Exception e){
			   if (transaction != null)
				   transaction.rollback();
			   if (session != null)
				   session.close();
		  
		   }
	   }
	   
	   /**
	    * Insert new object to the database.
	    * @param obj
	    * @return
	    */
	   public synchronized static Serializable insertDB(Object obj){
		   Session session=null;
		   Transaction transaction=null;
		   try{
			   session = getSession();
			   transaction = session.beginTransaction();
			   Serializable ans = session.save(obj);
			   transaction.commit();
			   session.flush();
			   session.close();
			   return ans;
		   }
		   catch(Exception e){
			   if (transaction != null)
				   transaction.rollback();
			   if (session != null)
				   session.close();
			   return null;
		   }
	   }
	   
	   public static User retrieveUser(String username){
		   Session session=null;
		   Transaction transaction=null;
	try{  
		   session = getSession();
		   transaction = session.beginTransaction();
		   Query q = session.createQuery("from User as u where u._username = :username");
		   q.setParameter("username", username);
		   List<User> tUserList= q.list();
		   session.close();
		   
		   if (tUserList.size() == 0)
			   	return null;
		   else return tUserList.get(0);
	}catch(Exception e){
		if (transaction != null)
			   transaction.rollback();
		if (session != null)
			session.close();

		return null;
	}
		
	   }
	   
	   public static List<User> retrieveOnlineUsers(){
		   Session session=null;
		   Transaction transaction = null;
		 try{ 
		   session = getSession();
		   transaction = session.beginTransaction();
		   Query q = session.createQuery("from User as u where u._status= :status");
		   q.setParameter("status", "ONLINE");
		   List<User> tAns = q.list();
		   
		   return tAns;
		   
		 }catch (Exception e){
			 if (transaction != null)
				   transaction.rollback();
			 if (session != null)
				session.close();
			 return null;
			 
		 }
	   }
	   
	   public static List<User> retrieveUsers(){
		   Session session=null;
		   Transaction transaction = null;
			 try{  
			   session = getSession();
			   transaction = session.beginTransaction();
			   Query q = session.createQuery("from User");
			   List<User> tAns = q.list();
			   
			   return tAns;
			   
			 }catch (Exception e){
				 if (transaction != null)
					   transaction.rollback();
				 if (session != null)
					session.close();
				 return null;
				 
			 }
		   }
	   
	   public static Forum retrieveForum(int id){
		   Session session=null;
		   Transaction transaction = null;
		   try{
		   session = getSession();
		   transaction = session.beginTransaction();
		   Query q = session.createQuery("from Forum as f where f._forumId= :id");
		   q.setParameter("id", id);
		   List<Forum> tAns = q.list();
		   
		   if (tAns.size() == 0)
			   	return null;
		   else return tAns.get(0);
		   
		  
		   }catch (Exception e){
			   if (transaction != null)
				   transaction.rollback();
			   if (session != null)
				session.close();
		   }
		  return null;
	  }
	   
	   public static List<Forum> retrieveForumsList(){
		   Session session=null;
		   Transaction transaction = null;
		  try{  session = getSession();
		    transaction = session.beginTransaction();
		   return session.createQuery("from Forum").list();
		 
		  }catch (Exception e){
			  if (transaction != null)
				   transaction.rollback();
			 if (session != null)
				session.close();
			  return null;
		  }
	  }
	   
	   public static List<Thread> retrieveThreadList(int ForumId){
		   Session session=null;
		   Transaction transaction = null;
		   try{ session = getSession();
		    transaction = session.beginTransaction();
		   Query q = session.createQuery("from Thread as t where t._forumId= :id");
		   q.setParameter("id", ForumId);
		   List<Thread> tAns = q.list();
		   
		   return tAns;
		   
		   }catch(Exception e){
			   if (transaction != null)
				   transaction.rollback();
		       if (session != null)
				session.close();
			   return null;
		   }
		   
	  }
	   
	   public static List<Post> retrievePostList(int threadId){
		   
		   Session session=null;
		   Transaction transaction = null;
		   try{
			   
			   session = getSession();
			   transaction = session.beginTransaction();

			   Query q = session.createQuery("from Post as p where p._threadID= :id");
			   q.setParameter("id", threadId);
			   List<Post> tAns = q.list();
			   
			   return tAns;
		
		   }
		   catch (Exception e){
			   if (transaction != null)
				   transaction.rollback();
		       if (session != null)
				session.close();
	
			   return null;
		   }
	  }

	public static Thread retrieveThread(int threadId) {
		
		Session session=null;
		Transaction transaction = null;
		//			can we change the db query to receive only one thread?
		try{
			   
			session = getSession();
			transaction = session.beginTransaction();
			
			  Query q = session.createQuery("from Thread as t where t._threadID= :id");
			  q.setParameter("id", threadId);
			  List<Thread> tAns = q.list();
			  
			  if (tAns.size() == 0)
			   	return null;
			  else return tAns.get(0);
		}
		catch(Exception e){
			 if (transaction != null)
				   transaction.rollback();
		       if (session != null)
				session.close();

			return null;
		}
	}
	
	public static List<User> retrieveUserFriends(String username) {
		Session session=null;
		Transaction transaction = null;
		try{
			   
			session = getSession();
			transaction = session.beginTransaction();
			
			  Query q = session.createQuery("from User as u where u._username= :id");
			  q.setParameter("id", username);
			  
			  return q.list();
			 
		}
		catch(Exception e){
			if (transaction != null)
				   transaction.rollback();
		       if (session != null)
				session.close();
			return null;
		}
	}
	

public static Post retrievePost(int postId) {
	Session session=null;
	Transaction transaction = null;
		//			can we change the db query to receive only one thread?
		try{
			   
			session = getSession();
			transaction = session.beginTransaction();
			
			  Query q = session.createQuery("from Post as p where p._post_id= :id");
			  q.setParameter("id", postId);
			  List<Post> tAns = q.list();
			  
			  if (tAns.size() == 0)
			   	return null;
			  else return tAns.get(0);
		}
		catch(Exception e){
			if (transaction != null)
				   transaction.rollback();
		     if (session != null)
				session.close();
			return null;
		}
	}
	
	public static List<Thread> retrieveAllThreadsList() {
		Session session=null;
		Transaction transaction = null;
		try{
			   
			session = getSession();
			transaction = session.beginTransaction();

			List<Thread> tThreadList = session.createQuery("from Thread").list();
		
			return tThreadList;
		}
		catch(Exception e){
			if (transaction != null)
				   transaction.rollback();
		     if (session != null)
				session.close();
			return null;
		}
	}

	
	public static User retrievePostOwner(int postId){
		Session session=null;
		Transaction transaction = null;
		try{
			   
			session = getSession();
			transaction = session.beginTransaction();
			
			 Query q = session.createQuery("from Post as p where p._post_id = :id");
			 q.setParameter("id", postId);
			 List<Post> tAns = q.list();
			  
			 if (tAns.size() == 0)
				   	return null;
				  else return tAns.get(0).get_Owner();
	
	
		}
		catch(Exception e){
			if (transaction != null)
				   transaction.rollback();
		     if (session != null)
				session.close();
			return null;
		}
	}
	
	public static void runQuery(String str){
		Session session=null;
		Transaction transaction = null;
		try{
			   
			session = getSession();
			transaction = session.beginTransaction();
			
			Query q = session.createSQLQuery(str);
			q.executeUpdate();
			transaction.commit();
			session.close();
			
		}
		catch(Exception e){
			if (transaction != null)
				   transaction.rollback();
		     if (session != null)
				session.close();

		
		}
	}
	
	public static int deletePost(int postId){
		Session session=null;
		Transaction transaction = null;
		try{
			   
			session = getSession();
			transaction = session.beginTransaction();
		   	Query q = session.createQuery("delete from Post as p where p._post_id = :postId");
			q.setParameter("postId", postId);
			int row_count = q.executeUpdate();
			transaction.commit();
			session.close();
			return row_count;
			
		}
		catch(Exception e){
			if (transaction != null)
				   transaction.rollback();
		     if (session != null)
				session.close();
			return 0;
		
		}
	}
	
	public static int deleteThread(int threadId){
		Session session=null;
		Transaction transaction = null;
		try{
		
			session = getSession();
			transaction = session.beginTransaction();
			Query q;
			q = session.createSQLQuery("delete from thread_watchers where thread_id=" + String.valueOf(threadId));
			q.executeUpdate();
			
			q = session.createQuery("delete from Thread as t where t._threadID = :threadId");
			q.setParameter("threadId", threadId);
			int row_count = q.executeUpdate();
			System.out.println("row_count = " +row_count);
			transaction.commit();
			session.close();
			
			return row_count;
			
		}
		catch(Exception e){
			if (transaction != null)
				   transaction.rollback();
		     if (session != null)
				session.close();
			return 0;
		
		}
	}
	
	
	public static List<Post> retrieveUserPosts(String username){
		Session session=null;
		Transaction transaction = null;
		User tUser = retrieveUser(username);
		try{
			   
			session = getSession();
			transaction = session.beginTransaction();
			
			Query q = session.createQuery("from Post as p where p._owner._username = :_username");
			q.setParameter("_username", username);
			List<Post> tPostList = q.list();
			
			session.close();
			
			return tPostList;
			
			
		}
		catch(Exception e){
			if (transaction != null)
				   transaction.rollback();
		     if (session != null)
				session.close();
			return null;
		}
		
	}
	
	
}
