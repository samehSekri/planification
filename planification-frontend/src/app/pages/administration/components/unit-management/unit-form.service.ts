/**
 * Dao pour les services Unit.
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

import { Unit } from 'app/shared/model/unit.model';
import { UniteTypeEnum } from '../../../../shared/model/enumeration/unitetype.enum';
import { TreeNode } from 'primeng/primeng';


@Injectable()
export class UnitManagementService {

  private actionUrl: string;


  constructor(
    private http: AuthHttp,
    private authenticationService: AuthenticationService,
    private configuration: Configuration,
    private router: Router) {
    /**
     * Define the Path of API Rest Controller For Unit service
     */
    this.actionUrl = configuration.serverWithApiUrl + 'unites';
  }

  /**
   * Get all Unit
   * @returns Unit[]
   */
  public getAll = (): Observable<any[]> => {
    return this.http.get(this.actionUrl, this.options())
      .map((response: Response) => <any[]>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Get Unit By type
   * @param type
   *           
   */
  public getByType = (type: UniteTypeEnum): Observable<Unit[]> => {
    return this.http.get(this.actionUrl +"/" + type, this.options())
      .map((response: Response) => <Unit[]>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }
  public getByParent = (parent: Unit): Observable<Unit[]> => {
    return this.http.post(this.actionUrl +"/parents" , parent, this.options())
      .map((response: Response) => <Unit[]>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Get Unit By name
   * @param name
   *            the Unitname of Unit
   * @returns UnitDto
   */
  public getByName = (name: string): Observable<Unit> => {
    return this.http.get(this.actionUrl + name, this.options())
      .map((response: Response) => <Unit>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Add new Unit
   * @param itemToAdd
   *              the Unit to add
   * @returns the new Unit
   */
  public add = (itemToAdd: Unit,type:String): Observable<Unit> => {
    return this.http.post(this.actionUrl+"/"+type, itemToAdd, this.options())
      .map((response: Response) => <Unit>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Update a Unit
   * @param itemToUpdate
   *        the Unit to update
   * @returns the updated Unit
   */
  public update = (itemToUpdate: Unit): Observable<Unit> => {
    return this.http.put(this.actionUrl, itemToUpdate, this.options())
      .map((response: Response) => <Unit>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Delete a Unit by name
   * @param name
   *        the name of Unit to delete
   */
  public delete = (name: String): Observable<Response> => {
    return this.http.delete(this.actionUrl + name, this.options())
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
