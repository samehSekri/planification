import { Injectable } from '@angular/core';
import { Http, RequestOptions, Response, Headers } from '@angular/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';
import { AuthHttp } from "angular2-jwt/angular2-jwt";
import { AuthenticationService } from "app/authentication";
import { Configuration } from "app/app.constants";

import { Router } from "@angular/router";


@Injectable()
export class PolyvalenceFichierService {

  private subscribers = [];
  public actionUrl : String;

  constructor(private http : AuthHttp,
    private authenticationService: AuthenticationService,
    private configuration: Configuration,
    private router: Router  ) {
 
      /**
     * Define the Path of API Rest Controller For Operator service
     */
    this.actionUrl = configuration.serverWithApiUrl + 'files';
     
}

private headers = null; //new Headers({'Content-Type' : 'multipart/form-data'});


public uploadExcel = (file: FormData): Observable<Response> => {

 // return this.http.post(this.actionUrl + '/upload-excel', file, options)
  return this.http.post(this.actionUrl + '/upload-excel', file , this.options()).map(res => res.json().data);
 // return this.http.post(this.actionUrl + '/upload-excel', file, options)
 //   .catch((response: Response) => this.errorHandler(response));
}

public sendFile = (file: File) : Observable<Response> => {
  let data: FormData = new FormData();
  data.append(`data`, file);
  
  let cpHeaders = new Headers();
    cpHeaders.append('Content-Type', 'application/json');
    cpHeaders.append('Accept', `application/json`);
    cpHeaders.append('Authorization', this.authenticationService.getToken() );
    let options = new RequestOptions({ headers: cpHeaders});

  return this.http.post(this.actionUrl + '/importFiles', data , options).map(res => res.json().data);
 
 
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
public options() {
  let headers = new Headers({ 'Authorization': this.authenticationService.getToken() });
  let options = new RequestOptions({ headers: headers });

  return options;
}

 
}