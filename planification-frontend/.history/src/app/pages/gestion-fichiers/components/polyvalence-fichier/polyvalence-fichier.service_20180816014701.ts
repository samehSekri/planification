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


  public uploadExcel = (file: FormData): Observable<Response> => {

    // return this.http.post(this.actionUrl + '/upload-excel', file, options)
    return this.http.post(this.actionUrl + '/upload-excel', file, this.options()).map(res => res.json().data);
    // return this.http.post(this.actionUrl + '/upload-excel', file, options)
    //   .catch((response: Response) => this.errorHandler(response));
  }


  postFormData(file: File) {
    return Observable.fromPromise(new Promise((resolve, reject) => {
      let formData: any = new FormData()
      let xhr = new XMLHttpRequest()

      formData.append("file", file, file.name)

      xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
          if (xhr.status === 200) {
            resolve(xhr.response)
          } else {
            reject(xhr.response)
          }
        }
      }
      xhr.open("POST", this.actionUrl + '/uploaddata', true);
      xhr.withCredentials = true;
      xhr.setRequestHeader('Authorization', 'Bearer ' + this.authenticationService.getToken());
      xhr.send(formData)
    }));
  }

  upload(file: File): Observable<string | number> {

    let fd: FormData = new FormData();

    fd.append("file", file);
    let xhr = new XMLHttpRequest;
    return Observable.create(observer => {
      xhr.addEventListener("progress", (progress) => {
        let percentCompleted;
        // Checks if we can really track the progress
        if (progress.lengthComputable) {
          // progress.loaded is a number between 0 and 1, so we'll multiple it by 100
          percentCompleted = Math.round(progress.loaded / progress.total * 100);
          if (percentCompleted < 1) {
            observer.next(0);
          } else {
            // Emit the progress percentage
            observer.next(percentCompleted);
          }
        }
      });

      xhr.addEventListener("load", (e) => {
        if (e.target['status'] !== 200) observer.error(e.target['responseText']);
        else observer.complete(e.target['responseText']);
      });
      xhr.addEventListener("error", (err) => {
        console.log('upload error', err);
        observer.error('Upload error');
      });

      xhr.addEventListener("abort", (abort) => {
        console.log('upload abort', abort);
        observer.error('Transfer aborted by the user');
      });

      //xhr.open('POST', 'http://some-dummy-url.com/v1/media/files');
      // Add any headers if necessary
      xhr.open("POST", this.actionUrl + '/uploaddata');
      xhr.withCredentials = true;
      xhr.setRequestHeader('Authorization', 'Bearer ' + this.authenticationService.getToken());

      // Send off the file
      xhr.send(fd);

      // This function will get executed once the subscription
      // has been unsubscribed
      return () => xhr.abort()
    });
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