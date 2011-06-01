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

function addThread()
{
	$.get('addThread',function(data) {
		$('#windowToLoad').html(data);
	});
}

function loadPostsList(threadId)
{
	$.get('postsList', {id : threadId}, 
		function(data) {
			$('#windowToLoad').html(data);
	});
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