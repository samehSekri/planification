/**
 * Dao pour les services user.
 * 
 * @author Wael TRABELSI
 *
 */
import { Injectable } from '@angular/core';
import { Http, RequestOptions, Response, Headers } from '@angular/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';
import { AuthHttp } from "angular2-jwt/angular2-jwt";
import { AuthenticationService } from "app/authentication";
import { Configuration } from "app/app.constants";
import { User } from "app/shared/model";
import { Router } from "@angular/router";

@Injectable()
export class UserManagementService {

  private actionUrl: string;

  constructor(
    private http: AuthHttp,
    private authenticationService: AuthenticationService,
    private configuration: Configuration,
    private router: Router) {
    /**
     * Define the Path of API Rest Controller For user service
     */
    this.actionUrl = configuration.serverWithApiUrl + 'users/';
  }

  /**
   * Get all users
   * @returns User[]
   */
  public getAll = (): Observable<User[]> => {
    return this.http.get(this.actionUrl, this.options())
      .map((response: Response) => <User[]>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Get User By Id
   * @param id
   *            the id of user
   * @returns userDto
   */
  public getById = (id: number): Observable<User> => {
    return this.http.get(this.actionUrl + "user/" + id, this.options())
      .map((response: Response) => <User>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Get User By username
   * @param username
   *            the username of user
   * @returns userDto
   */
  public getByUserName = (username: string): Observable<User> => {
    return this.http.get(this.actionUrl + username, this.options())
      .map((response: Response) => <User>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Add new user
   * @param itemToAdd
   *              the user to add
   * @returns the new user
   */
  public add = (itemToAdd: User): Observable<User> => {
    return this.http.post(this.actionUrl, itemToAdd, this.options())
      .map((response: Response) => <User>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Update a user
   * @param itemToUpdate
   *        the user to update
   * @returns the updated user
   */
  public update = (itemToUpdate: User): Observable<User> => {
    return this.http.put(this.actionUrl, itemToUpdate, this.options())
      .map((response: Response) => <User>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Delete a user by id
   * @param id
   *        the id of user to delete
   */
  public delete = (id: number): Observable<Response> => {
    return this.http.delete(this.actionUrl + id, this.options())
    .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * change user status by id
   * @param id the id of the user to change status
   * @type : number
   */
  public changeUserStatus = (id: number): Observable<User> => {
    return this.http.put(this.actionUrl + "user/" + id, this.options)
      .map((response: Response) => <User>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   *  throw the exception and redirect to login page when error is ERR_CONNECTION_REFUSED OR 401
   * @param error 
   */
  private errorHandler(error: Response): any {
      console.log(error);
      if (error.status == 0) {
          //ERR_CONNECTION_REFUSED
          this.router.navigate(['/login']);
      }else if (error.status === 401) {
          this.router.navigate(['/login']);
      }
      return Observable.throw(error);
  }

  /**
   * @returns RequestOptions
   *                  that include the Autorization token
   */
  private options() {
    let headers = new Headers({ 'Authorization': this.authenticationService.getToken() });
    let options = new RequestOptions({ headers: headers });

    return options;
  }
}
