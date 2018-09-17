/**
 * Dao pour les services quai.
 * 
 *
 */
import { Injectable } from '@angular/core';
import { Http, RequestOptions, Response, Headers } from '@angular/http';
import 'rxjs';
import { Observable } from 'rxjs/Observable';
import { AuthHttp } from "angular2-jwt";
import { AuthenticationService } from "../../../../authentication";
import { Configuration } from "../../../../app.constants";
import { Router } from "@angular/router";
import { Unit } from '../../../../shared/model/unit.model';
import { Scenario } from '../../../../shared/model/scenario.model';
import { StatutTypeEnum } from '../../../../shared/model/enumeration/statut.enum';

@Injectable()
export class ConsultationScenarioService {

    private actionUrl: string;

    constructor(
        private http: AuthHttp,
        private authenticationService: AuthenticationService,
        private configuration: Configuration,
        private router: Router) {
        
        this.actionUrl = configuration.serverWithApiUrl + 'scenarios';
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
   * Get all Unit
   * @returns Unit[]
   */
  public getAll = (): Observable<any[]> => {
    return this.http.get(this.actionUrl, this.options())
      .map((response: Response) => <any[]>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }

  public getByUnite = (unite: Unit): Observable<Scenario[]> => {
    return this.http.post(this.actionUrl  +"/scenario" ,unite, this.options())
      .map((response: Response) => <Scenario[]>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }
  public getByStatut = (statut: StatutTypeEnum): Observable<Scenario[]> => {
    return this.http.post(this.actionUrl  +"/scenarioStatut" ,statut, this.options())
      .map((response: Response) => <Scenario[]>response.json())
      .catch((response: Response) => this.errorHandler(response));
  }
  public getByDate = (date: Date): Observable<Scenario[]> => {
    return this.http.post(this.actionUrl  +"/scenarioDate" ,date, this.options())
      .map((response: Response) => <Scenario[]>response.json())
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
        } else if (error.status === 401) {
            this.router.navigate(['/login']);
        }
        return Observable.throw(error);
    }

}