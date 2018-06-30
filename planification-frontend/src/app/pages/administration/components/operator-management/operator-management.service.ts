/**
 * Dao pour les services Operator.
 * 
 *
 *
 */
import { Injectable } from '@angular/core';
import { Http, RequestOptions, Response, Headers } from '@angular/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';
import { AuthHttp } from "angular2-jwt/angular2-jwt";
import { AuthenticationService } from "app/authentication";
import { Configuration } from "app/app.constants";

import { Router } from "@angular/router";
import { Operator } from 'app/shared/model/operator.model';

@Injectable()
export class OperatorManagementService {

  private actionUrl: string;


  constructor(
    private http: AuthHttp,
    private authenticationService: AuthenticationService,
    private configuration: Configuration,
    private router: Router) {
    /**
     * Define the Path of API Rest Controller For Operator service
     */
    this.actionUrl = configuration.serverWithApiUrl + 'operateurs';
  }

  /**
   * Get all Operators
   * @returns Operator[]
   */
  public getAll = (): Observable<Operator[]> => {
    return this.http.get(this.actionUrl, this.options())
      .map((response: Response) => <Operator[]>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Get Operator By matricule
   * @param matricule
   *            the matricule of Operator
   * @returns OperatorDto
   */
  public getById = (matricule: String): Observable<Operator> => {
    return this.http.get(this.actionUrl + "Operator/" + matricule, this.options())
      .map((response: Response) => <Operator>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Get Operator By name
   * @param name
   *            the Operatorname of Operator
   * @returns OperatorDto
   */
  public getByOperatorName = (Operatorname: string): Observable<Operator> => {
    return this.http.get(this.actionUrl + Operatorname, this.options())
      .map((response: Response) => <Operator>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Add new Operator
   * @param itemToAdd
   *              the Operator to add
   * @returns the new Operator
   */
  public add = (itemToAdd: Operator): Observable<Operator> => {
    return this.http.post(this.actionUrl, itemToAdd, this.options())
      .map((response: Response) => <Operator>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Update a Operator
   * @param itemToUpdate
   *        the Operator to update
   * @returns the updated Operator
   */
  public update = (itemToUpdate: Operator): Observable<Operator> => {
    return this.http.put(this.actionUrl, itemToUpdate, this.options())
      .map((response: Response) => <Operator>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Delete a Operator by id
   * @param id
   *        the id of Operator to delete
   */
  public delete = (matricule: String): Observable<Response> => {
    return this.http.delete(this.actionUrl+"/"+ matricule, this.options())
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
