function checkNotifications()
{
	$.get('notifications', function(data) {
		$('#notifications').html(data);
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
	$.get('editPost',{numPostEdit: numPost},
	function(data) {
		$('#windowToLoad').html(data);
	});
}
// we will call the parameter id in the postsListServlet using the getParameter("id") method 
function loadPostsList(threadId)
{
	$.get('postsList', {id : threadId}, 
		function(data) {
			$('#windowToLoad').html(data);
	});
}

function loadEditListPosts(threadId)
{
	$.get('postsList', {idThread : threadId,title: $('#postTitleEdit').val(), body: $('#postBodyEdit').val()}, 
			function(data) {
				$('#windowToLoad').html(data);
		});
	alert("finish");
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

$(document).ready(function()
{
	setTimeout('checkNotifications()', 10);
	loadForumsList();
	loadFriendsList();
});

function deletePost(postId)
{
	$.get('postsList', {deletePostId : postId}, 
		function(data) {
			$('#windowToLoad').html(data);
	});
}
