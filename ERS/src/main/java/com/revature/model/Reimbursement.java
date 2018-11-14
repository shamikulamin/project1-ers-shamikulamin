package com.revature.model;

public class Reimbursement {
	private int id;
	private int amount;
	private String submittedTime;
	private String resolved;
	private String description;
	private String receipt;
	
	private String authorName;
	private String resolverName;
	private String reimbStatus;
	private String reimbType;
	
	private int author;
	private int resolver;
	private int reimbStatusId;
	private int reimbTypeId;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int id, int amount, String submittedTime, String resolved, String description, String receipt,
			String authorName, String resolverName, String reimbStatus, String reimbType, int author, int resolver,
			int reimbStatusId, int reimbTypeId) {
		super();
		this.id = id;
		this.amount = amount;
		this.submittedTime = submittedTime;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.authorName = authorName;
		this.resolverName = resolverName;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
		this.author = author;
		this.resolver = resolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSubmittedTime() {
		return submittedTime;
	}

	public void setSubmittedTime(String submittedTime) {
		this.submittedTime = submittedTime;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getResolverName() {
		return resolverName;
	}

	public void setResolverName(String resolverName) {
		this.resolverName = resolverName;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public String getReimbType() {
		return reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + author;
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + reimbStatusId;
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + reimbTypeId;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolver;
		result = prime * result + ((resolverName == null) ? 0 : resolverName.hashCode());
		result = prime * result + ((submittedTime == null) ? 0 : submittedTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount != other.amount)
			return false;
		if (author != other.author)
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbStatusId != other.reimbStatusId)
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (reimbTypeId != other.reimbTypeId)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver != other.resolver)
			return false;
		if (resolverName == null) {
			if (other.resolverName != null)
				return false;
		} else if (!resolverName.equals(other.resolverName))
			return false;
		if (submittedTime == null) {
			if (other.submittedTime != null)
				return false;
		} else if (!submittedTime.equals(other.submittedTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submittedTime=" + submittedTime + ", resolved="
				+ resolved + ", description=" + description + ", receipt=" + receipt + ", authorName=" + authorName
				+ ", resolverName=" + resolverName + ", reimbStatus=" + reimbStatus + ", reimbType=" + reimbType
				+ ", author=" + author + ", resolver=" + resolver + ", reimbStatusId=" + reimbStatusId
				+ ", reimbTypeId=" + reimbTypeId + "]";
	}
	
	
}
