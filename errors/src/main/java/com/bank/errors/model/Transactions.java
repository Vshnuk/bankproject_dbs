package com.bank.errors.model;

	import java.sql.Timestamp;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.Table;

	import org.springframework.stereotype.Component;
	@Component
	@Entity
	@Table(name="transactions")
	public class Transactions {
		@Id
		@Column(name="tno")
		private int tno;
		public int getTno() {
			return tno;
		}
		public void setTno(int tno) {
			this.tno = tno;
		}
		@Column(name="from_acc")
		private int fromAccountId;
		@Column(name="to_acc")
		private int toAccountId;
		@Column(name="amount")
		private double amount;
		@Column(name="type")
		private String type;
		@Column(name="trans_id")
		private int transaction_id;
		@Column(name="trans_date")
		private Timestamp timestamp;
		public int getFromAccountId() {
			return fromAccountId;
		}
		public void setFromAccountId(int fromAccountId) {
			this.fromAccountId = fromAccountId;
		}
		public int getToAccountId() {
			return toAccountId;
		}
		public void setToAccountId(int toAccountId) {
			this.toAccountId = toAccountId;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getTransaction_id() {
			return transaction_id;
		}
		public void setTransaction_id(int transaction_id) {
			this.transaction_id = transaction_id;
		}
		public Timestamp getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Timestamp timestamp) {
			this.timestamp = timestamp;
		}
	


}
