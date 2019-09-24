package ua.vlasovEugene.servletBankSystem.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity Class of table 'credit_opening_request'
 * */
public class CreditOpeningRequest {

  private Integer requestId;
  private String userEmailLogin;
    private BigDecimal userTotalBalance;
    private BigDecimal expectedCreditLimit;
    private LocalDateTime dateOfEndCredit;

  public CreditOpeningRequest() {
  }

    public CreditOpeningRequest(Integer requestId, String userEmailLogin,
                                BigDecimal userTotalBalance, BigDecimal expectedCreditLimit,
                                LocalDateTime dateOfEndCredit) {
    this.requestId = requestId;
    this.userEmailLogin = userEmailLogin;
        this.userTotalBalance = userTotalBalance;
        this.expectedCreditLimit = expectedCreditLimit;
        this.dateOfEndCredit = dateOfEndCredit;
  }

  public Integer getRequestId() {
    return requestId;
  }

  public void setRequestId(Integer requestId) {
    this.requestId = requestId;
  }

  public String getUserEmailLogin() {
    return userEmailLogin;
  }

  public void setUserEmailLogin(String userEmailLogin) {
    this.userEmailLogin = userEmailLogin;
  }

    public BigDecimal getUserTotalBalance() {
        return userTotalBalance;
    }

    public void setUserTotalBalance(BigDecimal userTotalBalance) {
        this.userTotalBalance = userTotalBalance;
    }

    public BigDecimal getExpectedCreditLimit() {
        return expectedCreditLimit;
    }

    public void setExpectedCreditLimit(BigDecimal expectedCreditLimit) {
        this.expectedCreditLimit = expectedCreditLimit;
    }

    public LocalDateTime getDateOfEndCredit() {
        return dateOfEndCredit;
  }

    public void setDateOfEndCredit(LocalDateTime dateOfEndCredit) {
        this.dateOfEndCredit = dateOfEndCredit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CreditOpeningRequest that = (CreditOpeningRequest) o;
    return Objects.equals(requestId, that.requestId) &&
            Objects.equals(userEmailLogin, that.userEmailLogin) &&
            Objects.equals(userTotalBalance, that.userTotalBalance) &&
            Objects.equals(expectedCreditLimit, that.expectedCreditLimit) &&
            Objects.equals(dateOfEndCredit, that.dateOfEndCredit);
  }

  @Override
  public int hashCode() {
      return Objects.hash(requestId, userEmailLogin, userTotalBalance,
              expectedCreditLimit, dateOfEndCredit);
  }

    @Override
    public String toString() {
        return "CreditOpeningRequest{" +
                "requestId=" + requestId +
                ", userEmailLogin='" + userEmailLogin + '\'' +
                ", userTotalBalance=" + userTotalBalance +
                ", expectedCreditLimit=" + expectedCreditLimit +
                ", dateOfEndCredit=" + dateOfEndCredit +
                '}';
  }
}
