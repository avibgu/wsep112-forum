/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import common.forum.items.PostInfo;
import common.forum.items.UserInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author yedidim
 */
public class DemoClientController {
public   Vector<PostInfo> getPostsList() {

        int size = (int) (Math.random()*10);

     //   ArrayList<PostInfo> list = new ArrayList<PostInfo>(size);
        Vector <PostInfo> list=new Vector<PostInfo>(0,1);

        for (int i = 0; i < size; i++){

            list.add(new PostInfo(i, "title"+ i, "body" +1, new UserInfo ("in","avi" +1),
			i, "time "+i));
        }

        return list;
    }
}
