/**
 * Dao pour les services Article.
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
import { Article } from 'app/shared/model/article.model';


@Injectable()
export class ArticleManagementService {

  private actionUrl: string;


  constructor(
    private http: AuthHttp,
    private authenticationService: AuthenticationService,
    private configuration: Configuration,
    private router: Router) {
    /**
     * Define the Path of API Rest Controller For Article service
     */
    this.actionUrl = configuration.serverWithApiUrl + 'articles';
  }

  /**
   * Get all Article
   * @returns Article[]
   */
  public getAll = (): Observable<Article[]> => {
    return this.http.get(this.actionUrl, this.options())
      .map((response: Response) => <Article[]>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }
 /**
   * change article status by reference
   * @param reference the id of the article to change status
   * @type : number
   */
  public changeArticleStatus = (reference: String): Observable<Article> => {
    return this.http.put(this.actionUrl + "/article/" + reference, this.options)
      .map((response: Response) => <Article>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }
  /**
   * Get Article By reference
   * @param reference
   *            the reference of Article
   * @returns ArticleDto
   */
  public getById = (reference: String): Observable<Article> => {
    return this.http.get(this.actionUrl + "reference/" + reference, this.options())
      .map((response: Response) => <Article>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

 
  /**
   * Add new Article
   * @param itemToAdd
   *              the Article to add
   * @returns the new Article
   */
  public add = (itemToAdd: Article): Observable<Article> => {
    return this.http.post(this.actionUrl, itemToAdd, this.options())
      .map((response: Response) => <Article>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Update a Article
   * @param itemToUpdate
   *        the Article to update
   * @returns the updated Article
   */
  public update = (itemToUpdate: Article): Observable<Article> => {
    return this.http.put(this.actionUrl, itemToUpdate, this.options())
      .map((response: Response) => <Article>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  /**
   * Delete a Article by id
   * @param id
   *        the id of Article to delete
   */
  public delete = (reference: String): Observable<Response> => {
    return this.http.delete(this.actionUrl+"/"+ reference, this.options())
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
