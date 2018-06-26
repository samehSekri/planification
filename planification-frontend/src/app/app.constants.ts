import { Injectable } from '@angular/core';
import { isDevMode } from '@angular/core';

@Injectable()
export class Configuration {
    public host: string = isDevMode() ? '127.0.0.1' : '';
    public port: string = isDevMode() ?  '8086' : '';
    public apiUrl: string = 'api/';
    public serverUrl: string = isDevMode() ? 'http://' + this.host + ':' + this.port + '/' : '';
    public serverWithApiUrl = isDevMode() ? this.serverUrl + this.apiUrl : "/" + this.apiUrl;
}