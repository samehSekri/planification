import { Injectable } from '@angular/core';
import { Http, RequestOptions, Response, Headers } from '@angular/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';
import { AuthHttp } from "angular2-jwt/angular2-jwt";
import { AuthenticationService } from "app/authentication";
import { Configuration } from "app/app.constants";

import { Router } from "@angular/router";


@Injectable()
export class AlgorithmeScenarioService {

  private subscribers = [];
  private actionUrl: String;

  constructor(private http: AuthHttp,
    private authenticationService: AuthenticationService,
    private configuration: Configuration,
    private router: Router) {

    /**
   * Define the Path of API Rest Controller For Operator service
   */
    this.actionUrl = configuration.serverWithApiUrl + 'files';

  }

  private headers = null; //new Headers({'Content-Type' : 'multipart/form-data'});


 


}