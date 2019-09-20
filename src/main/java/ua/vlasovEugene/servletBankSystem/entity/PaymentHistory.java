package ua.vlasovEugene.servletBankSystem.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity Class of table 'payment_history'
 * */
public class PaymentHistory {

  private Integer transactionId;
  private Long accountNumber;
  private BigDecimal transactionAmount;
  private BigDecimal currentBalance;
  private LocalDateTime dateOfTransaction;
  private String notification;

  public PaymentHistory() {
  }

  public PaymentHistory(Integer transactionId, Long accountNumber,
                        BigDecimal transactionAmount, BigDecimal currentBalance,
                        LocalDateTime dateOfTransaction, String notification) {
    this.transactionId = transactionId;
    this.accountNumber = accountNumber;
    this.transactionAmount = transactionAmount;
    this.currentBalance = currentBalance;
    this.dateOfTransaction = dateOfTransaction;
    this.notification = notification;
  }

  public Integer getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Integer transactionId) {
    this.transactionId = transactionId;
  }

  public Long getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(Long accountNumber) {
    this.accountNumber = accountNumber;
  }

  public BigDecimal getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(BigDecimal transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  public BigDecimal getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(BigDecimal currentBalance) {
    this.currentBalance = currentBalance;
  }

  public LocalDateTime getDateOfTransaction() {
    return dateOfTransaction;
  }

  public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
    this.dateOfTransaction = dateOfTransaction;
  }

  public String getNotification() {
    return notification;
  }

  public void setNotification(String notification) {
    this.notification = notification;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PaymentHistory that = (PaymentHistory) o;
    return Objects.equals(transactionId, that.transactionId) &&
            Objects.equals(accountNumber, that.accountNumber) &&
            Objects.equals(transactionAmount, that.transactionAmount) &&
            Objects.equals(currentBalance, that.currentBalance) &&
            Objects.equals(dateOfTransaction, that.dateOfTransaction) &&
            Objects.equals(notification, that.notification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, accountNumber, transactionAmount,
            currentBalance, dateOfTransaction, notification);
  }
}
