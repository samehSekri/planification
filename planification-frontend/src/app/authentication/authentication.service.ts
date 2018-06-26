import { Injectable } from '@angular/core';
import { tokenNotExpired, AuthHttp } from 'angular2-jwt';
import 'rxjs/add/operator/map';

import { Http, Headers, RequestOptions } from "@angular/http";
import { Observable } from "rxjs/Observable";
import { Configuration } from "app/app.constants";
import { User, Permission } from "app/shared/model";
import { RoleNameEnum } from "app/shared/model/enumeration";
import { Router } from "@angular/router";

@Injectable()
export class AuthenticationService {
  private actionUrl: string;
  private resetPasswordUrl: string;

  constructor(private http: AuthHttp, private configuration: Configuration, private router: Router) {
    this.actionUrl = configuration.serverUrl + 'auth';
  }

  /**
   * login service
   * @param credentials the creadentials (Login and password)
   */
  doLogin(credentials) {
    return this.http.post(this.actionUrl, credentials)
      .map(res => {
        const data = res.json();
        if (data) {
          localStorage.setItem('token', data.token);
          localStorage.setItem('user', JSON.stringify(data.user));
        }
      })
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
  *  throw the exception and redirect to login page when error is ERR_CONNECTION_REFUSED OR 401
  * @param error 
  */
  private errorHandler(error: Response): any {
    console.log(error);
    return Observable.throw(error);
  }

  /**
  * Reset user password
  * @param credentials : only 2 attributes are required for this function : 
  * the username : the username
  * and the email 
  */
  public resetPassword(credentials): Observable<any> {
    return this.http.put(this.actionUrl + "/reset-password", credentials)
      .map(
      res => res.json());
  }

  /**
   * set the header option by setting the JWT token into the header of the request
   * @return RequestOptions
   */
  private options() {
    let headers = new Headers({ 'Authorization': '' + localStorage.getItem('token') });
    let options = new RequestOptions({ headers: headers });
    return options;
  }

  /**
   * return the token of the current user
   */
  getToken() {
    var token = localStorage.getItem('token');
    return token ? token : "";
  }

  /**
   * Log out
   * Delete the currentUser info and token from the local storage
   */
  doLogout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }

  /**
   * check if user is still logged
   */
  isLoggedIn() {
    return tokenNotExpired('token');
  }

  /**
   * Get list of autorities for the currentUser (role && permissions)
   */
  getRoles(): any[] {
    const user: User = JSON.parse(localStorage.getItem('user'));
    if (!user) {
      this.router.navigate(['/login']);
    }
    return user.authorities;
  }

  /**
   * Get role name of the current user
   * @return RoleNameEnum 
   */
  getRoleName(): RoleNameEnum {
    const user: User = JSON.parse(localStorage.getItem('user'));
    if (!user) {
      this.router.navigate(['/login']);
    }
    return user.roleName;
    //return user.authorities[0].name;
  }

  /**
   * List of permission of the current user
   * @return arrayList of Permission
   */
  getPermissions(): Permission[] {
    const user: User = JSON.parse(localStorage.getItem('user'));
    if (!user) {
      this.router.navigate(['/login']);
    }
    return user.permissions;
  }

  /**
   * Get the current logged user
   * @return user
   */
  getCurrentUser(): User {
    const user: User = JSON.parse(localStorage.getItem('user'));
    if (!user) {
      this.router.navigate(['/login']);
    }
    return user;
  }

  /**
   * check if the logged user has role admin
   * @return boolean
   */
  public hasRoleAdmin(): boolean {
    return this.getRoleName() == RoleNameEnum.ROLE_ADMIN;
  }

  /**
   * Update current user info
   * @param user 
   */
  public updateCurrentUser(user: User) {
    localStorage.setItem('user', JSON.stringify(user));
  }
}
