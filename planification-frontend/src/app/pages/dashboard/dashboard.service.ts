/**
 * Dao pour les services quai.
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
import { Router } from "@angular/router";

@Injectable()
export class DashboardService {

    private actionUrl: string;

    constructor(
        private http: AuthHttp,
        private authenticationService: AuthenticationService,
        private configuration: Configuration,
        private router: Router) {
        
        this.actionUrl = configuration.serverWithApiUrl + 'dashboard/';
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


    /**
   *  throw the exception and redirect to login page when error is ERR_CONNECTION_REFUSED OR 401
   * @param error 
   */
    private errorHandler(error: Response): any {
        console.log(error);
        if (error.status == 0) {
            //ERR_CONNECTION_REFUSED
            this.router.navigate(['/login']);
        } else if (error.status === 401) {
            this.router.navigate(['/login']);
        }
        return Observable.throw(error);
    }

}