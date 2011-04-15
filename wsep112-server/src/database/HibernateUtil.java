package database;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
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
import java.util.HashSet;

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
		   List<User> tUserList= session.createQuery("from User").list();
		   for (int i=0; i < tUserList.size() ; ++i){
			   User tUser = tUserList.get(i);
			   if (tUser.getUserName().equals(username))
				   return tUser;
		   }
		 
		   session.close();
		   return null;
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
		
	   }
	   
	   public static List<User> retrieveOnlineUsers(){
		 try{  Session session = getSession();
		   Transaction transaction = session.beginTransaction();
		   List<User> tAns = session.createQuery("from User where status=ONLINE").list();
		   
		   return tAns;
		 }catch (Exception e){
			 e.printStackTrace();
			 return null;
			 
		 }
	   }
	   
	   public static Forum retrieveForum(int id){
		   try{Session session = getSession();
		   Transaction transaction = session.beginTransaction();
		   List<Forum> tForumList = session.createQuery("from Forum").list();
		  for (int i=0; i < tForumList.size() ; ++i){
			   Forum tForum = tForumList.get(i);
			   if (tForum.getForumId() == id)
				   return tForum;
		   }
		   }catch (Exception e){
			   e.printStackTrace();
		   }
		  return null;
	  }
	   
	   public static List<Forum> retrieveForumsList(){
		  try{ Session session = getSession();
		   Transaction transaction = session.beginTransaction();
		   return session.createQuery("from Forum").list();
		 /*  for (int i=0; i < tUserList.size() ; ++i){
			   User tUser = tUserList.get(i);
			   if (tUser.getUserName().equals(username))
				   return tUser;
		   }*/
		  }catch (Exception e){
			  e.printStackTrace();
			  return null;
		  }
	  }
	   
	   public static List<Thread> retrieveThreadList(int ForumId){
		   try{Session session = getSession();
		   Transaction transaction = session.beginTransaction();
		  // return session.createQuery("from Thread where forum_id="+ForumId).list();
		   List<Thread> tThreadList = session.createQuery("from Thread").list();
		   List<Thread> tAns = new ArrayList<Thread>();
		   for (int i=0; i < tThreadList.size() ; ++i){
			   Thread tThread = tThreadList.get(i);
			   if (tThread.get_forumId() == ForumId)
				   tAns.add(tThread);
		   }
		   return tAns;
		   }catch(Exception e){
			   e.printStackTrace();
			   return null;
		   }
		   
	  }
	   
	   public static List<Post> retrievePostList(int threadId){
		   try{Session session = getSession();
		   Transaction transaction = session.beginTransaction();
		   //return session.createQuery("from Post where forum_id="+ForumId +"and thread_id="+ threadId).list();
		   List<Post> tPostsList = session.createQuery("from Post").list();
		   List<Post> tAns = new ArrayList<Post>();
		  for (int i=0; i < tPostsList.size() ; ++i){
			   Post tPost = tPostsList.get(i);
			   if (tPost.getThread_id() == threadId)
				   tAns.add(tPost);
		   }
		   return tAns;
		   }catch (Exception e){
			   e.printStackTrace();
			   return null;
		   }
	  }
}