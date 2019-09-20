package ua.vlasovEugene.servletBankSystem.entity;


import java.util.Objects;

/**
 * Entity Class of table 'user_role'
 * */
public class UserRole {

  private Long userRoleId;
  private String userRoleValue;

  public UserRole() {
  }

  public UserRole(Long userRoleId, String userRoleValue) {
    this.userRoleId = userRoleId;
    this.userRoleValue = userRoleValue;
  }

  public Long getUserRoleId() {
    return userRoleId;
  }

  public void setUserRoleId(Long userRoleId) {
    this.userRoleId = userRoleId;
  }


  public String getUserRoleValue() {
    return userRoleValue;
  }

  public void setUserRoleValue(String userRoleValue) {
    this.userRoleValue = userRoleValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserRole userRole = (UserRole) o;
    return Objects.equals(userRoleId, userRole.userRoleId) &&
            Objects.equals(userRoleValue, userRole.userRoleValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userRoleId, userRoleValue);
  }
}
