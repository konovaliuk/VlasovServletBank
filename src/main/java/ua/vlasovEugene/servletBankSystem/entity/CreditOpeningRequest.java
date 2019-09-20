package ua.vlasovEugene.servletBankSystem.entity;


import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entity Class of table 'credit_opening_request'
 * */
public class CreditOpeningRequest {

  private Integer requestId;
  private String userEmailLogin;
  private BigDecimal percentOfAccount;

  public CreditOpeningRequest() {
  }

  public CreditOpeningRequest(Integer requestId, String userEmailLogin, BigDecimal percentOfAccount) {
    this.requestId = requestId;
    this.userEmailLogin = userEmailLogin;
    this.percentOfAccount = percentOfAccount;
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

  public BigDecimal getPercentOfAccount() {
    return percentOfAccount;
  }

  public void setPercentOfAccount(BigDecimal percentOfAccount) {
    this.percentOfAccount = percentOfAccount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CreditOpeningRequest that = (CreditOpeningRequest) o;
    return Objects.equals(requestId, that.requestId) &&
            Objects.equals(userEmailLogin, that.userEmailLogin) &&
            Objects.equals(percentOfAccount, that.percentOfAccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestId, userEmailLogin, percentOfAccount);
  }
}
