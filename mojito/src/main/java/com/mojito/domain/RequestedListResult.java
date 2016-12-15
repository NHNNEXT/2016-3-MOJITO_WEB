package com.mojito.domain;

import java.util.Set;

public class RequestedListResult {
	private Set<User> list;
	private String listName;
	private String requestedList;
	private String listBoxName;
	
	private RequestedListResult(Set<User> list, String listName, String requestedList, String listBoxName) {
		this.list = list;
		this.listName = listName;
		this.requestedList = requestedList;
		this.listBoxName = listBoxName;
	}
	
	public Set<User> getList() {
		return list;
	}

	public String getListName() {
		return listName;
	}

	public String getRequestedList() {
		return requestedList;
	}

	public String getListBoxName() {
		return listBoxName;
	}

	public static RequestedListResult getRequestsToUser(User user) {
		return new RequestedListResult(user.getRequestsToUser(), ".friend-request-list", 
				"#requestToUser", ".friend-request-box");
	}
	
	public static RequestedListResult getRequestsToMe(User user) {
		return new RequestedListResult(user.getRequestsToMe(), ".friend-request-list",
				"#requestToMe", ".friend-request-box");
	}
	
	public static RequestedListResult getFriendUsers(User user) {
		return new RequestedListResult(user.getFriendUsers(), ".friend-list",
				"#myFriend", ".friend-list-box");
	}
	
	public static RequestedListResult getMetUsers(User user) {
		return new RequestedListResult(user.getMetUsers(), ".friend-list",
				"#metUser", ".friend-list-box");
	}
	
	public static RequestedListResult getSearchResult(Set<User> result) {
		return new RequestedListResult(result, ".friend-search-list",
				"#metUser", ".friend-search-result-box");
	}
}
