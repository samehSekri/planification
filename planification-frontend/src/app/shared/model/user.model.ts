import { RoleNameEnum } from "app/shared/model/enumeration";
import { Permission } from "app/shared/model/permission.model";

export class User {
  /**
   * @param id 
   * @param username 
   * @param password 
   * @param firstname 
   * @param lastname 
   * @param email 
   * @param authorities
   * @param enabled 
   * @param lastPasswordResetDate 
   * @param roleName 
   * @param permissions
   */
  constructor(
  public id: number,
  public username: string,
  public password: string,
  public firstname: string,
  public lastname: string,
  public email: string,
  public authorities: any,
  public enabled: boolean,
  public lastPasswordResetDate: Date,
  public roleName: RoleNameEnum,
  public permissions: Permission[]
  ) {
  }
}
