import { Component, OnInit, Input } from '@angular/core';
import { AuthenticationService } from '../../../../authentication';
import { PolyvalenceFichierService } from './polyvalence-fichier.service';
import { ElementRef, ViewChild } from '@angular/core';
import { AuthHttp } from "angular2-jwt/angular2-jwt";
import { RequestOptions } from '@angular/http';

@Component({
  selector: 'app-polyvalence-fichier',
  templateUrl: './polyvalence-fichier.component.html',
  styleUrls: ['./polyvalence-fichier.component.scss']
})
export class PolyvalenceFichierComponent implements OnInit {

  @Input() multiple: boolean = false;
  @ViewChild('fileInput') inputEl: ElementRef;

  constructor(private http: AuthHttp,
    private auth: AuthenticationService,
    private polyvalenceService: PolyvalenceFichierService) { }


    ngOnInit() {
  }

  upload() {
    let inputEl: HTMLInputElement = this.inputEl.nativeElement;
    let fileCount: number = inputEl.files.length;
    let formData = new FormData();
    if (fileCount > 0) { // a file was selected
      for (let i = 0; i < fileCount; i++) {
        formData.append('file[]', inputEl.files.item(i));
      }

      
      this.http
        .post(this.polyvalenceService.actionUrl + "/upload", formData, this.polyvalenceService.options())
      // do whatever you do...
      // subscribe to observable to listen for response
    }
  }

  excelFileChange(event) {
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      const file = event.target.files.item(0)
      let formData: FormData = new FormData();

      formData.append('file', file);


      this.polyvalenceService.uploadExcel(file).subscribe(val => {
        alert(val);
      });

    }



    /*let fileList: FileList = event.target.files;
    if(fileList.length > 0) {
      let file: File = fileList[0];
      let fileSize:number=fileList[0].size;
      if(fileSize<=10485760)
      {
        let formData:FormData = new FormData();
        formData.append('file',file);
        formData.append('token',this.auth.getToken());
        this.polyvalenceService.uploadExcel(formData).subscribe(val => {
          alert(val);
        });
      }
      else
      {
        alert("File size is exceeded");
      }
    }
    else
    {
      alert("Something went Wrong.");
    }
    */
  }

  onSelectFile(event) {
    if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      let fileInformation = null;

      this.polyvalenceService.sendFile(file).subscribe(val => {
        console.log(val);
      });
    }
  }




}
