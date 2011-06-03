
//val currentThreadId = -1;

function checkNotifications()
{
	$.get('notifications', function(data) {
		$('#notifications').html(data);
	})
	.success(function(){
		
		/*if ($('#notificationContent').text() == "REFRESH" && currentThreadId != -1){
			
			alert("refreshing..");
			loadPostsList(currentThreadId);
		}*/
	});
	
	setTimeout('checkNotifications()', 10000);
}

function loadForumsList()
{
	$.get('forumsList', function(data) {
		$('#windowToLoad').html(data);
	});
}

function loadThreadsList(forumId)
{
	//currentThreadId = -1;
	
	$.get('threadsList', {id : forumId}, 
		function(data) {
			$('#windowToLoad').html(data);
	});
}

function loadAddedThread(forumId)
{
	$.get('threadsList', {idOfForum : forumId, title: $('#threadTitle').val(), body: $('#threadBody').val()}, 
		function(data) {
			$('#windowToLoad').html(data);
	});
}

function loadAddedPost(threadId)
{
	$.get('postsList', {id : threadId,title: $('#postTitle').val(), body: $('#postBody').val()}, 
			function(data) {
				$('#windowToLoad').html(data);
		});
}

function addThread()
{
	$.get('addThread',function(data) {
		$('#windowToLoad').html(data);
	});
}


function EditPostWindow(numPost)
{
	//currentThreadId = -1;
	
	$.get('editPost',{numPostEdit: numPost},
	function(data) {
		$('#windowToLoad').html(data);
	});
}
// we will call the parameter id in the postsListServlet using the getParameter("id") method 
function loadPostsList(threadId)
{
	//currentThreadId = threadId;
	
	$.get('postsList', {id : threadId}, 
		function(data) {
			$('#windowToLoad').html(data);
	});
}

function loadEditListPosts(threadId)
{
	//alert("here!");
	$.get('postsList', {idThread : threadId,title: $('#postTitleEdit').val(), body: $('#postBodyEdit').val()}, 
			function(data) {
				$('#windowToLoad').html(data);
		});
	alert("Your Message Has been changed!");
}

function loadFriendsList()
{
	$.get('friends', function(data) {
		$('#friendLoad').html(data);
	});
}

function addFriend()
{
	$.get('friends',{Addfriend : $('#addFriendName').val()},
		function(data) {
			$('#friendLoad').html(data);
	});
}

function removeFriend()
{
	$.get('friends',{Removefriend : $('#removeFriendName').val()},
		function(data) {
			$('#friendLoad').html(data);
	});
}

function deletePost(postId)
{
	$.get('postsList', {deletePostId : postId}, 
		function(data) {
			$('#windowToLoad').html(data);
	});
}

function deleteThread(threadId)
{
	$.get('threadsList', {deleteThreadId : threadId}, 
		function(data) {
			$('#windowToLoad').html(data);
	});
}


function addPost(threadId){
	
	//currentThreadId = -1;
	
	//TODO
}

$(document).ready(function()
{
	setTimeout('checkNotifications()', 10);
	loadForumsList();
	loadFriendsList();
});

