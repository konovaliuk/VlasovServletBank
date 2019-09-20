package ua.vlasovEugene.servletBankSystem.entity;


import java.util.Objects;

/**
 * Entity Class of table 'account_type'
 * */
public class AccountType {

  private Integer accountTypeId;
  private String accTypeValue;

  public AccountType() {
  }

  public AccountType(Integer accountTypeId, String accTypeValue) {
    this.accountTypeId = accountTypeId;
    this.accTypeValue = accTypeValue;
  }

  public Integer getAccountTypeId() {
    return accountTypeId;
  }

  public void setAccountTypeId(Integer accountTypeId) {
    this.accountTypeId = accountTypeId;
  }

  public String getAccTypeValue() {
    return accTypeValue;
  }

  public void setAccTypeValue(String accTypeValue) {
    this.accTypeValue = accTypeValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AccountType that = (AccountType) o;
    return Objects.equals(accountTypeId, that.accountTypeId) &&
            Objects.equals(accTypeValue, that.accTypeValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountTypeId, accTypeValue);
  }
}
