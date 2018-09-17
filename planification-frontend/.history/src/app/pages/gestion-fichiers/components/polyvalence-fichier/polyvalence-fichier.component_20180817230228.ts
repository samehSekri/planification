import { Component, OnInit, Input } from '@angular/core';
import { AuthenticationService } from '../../../../authentication';
import { PolyvalenceFichierService } from './polyvalence-fichier.service';
import { SlimLoadingBarModule } from 'ng2-slim-loading-bar';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';


@Component({
  selector: 'app-polyvalence-fichier',
  templateUrl: './polyvalence-fichier.component.html',
  styleUrls: ['./polyvalence-fichier.component.scss']
})
export class PolyvalenceFichierComponent implements OnInit {
  file: any;
  progress: number;
  consoleErr:String;

  constructor(private auth: AuthenticationService,private confirmationService=ConfirmationService,
    private polyvalenceService: PolyvalenceFichierService) { }



  ngOnInit() {
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

  getFiles(files: any) {
    let empDataFiles: FileList = files.files;
    this.file = empDataFiles[0];
  }

  postfile() {
    if (this.file !== undefined) {
      this.progress=100;
      this.polyvalenceService.postFormData(this.file).map(responce => {
        console.log("ok");
        
      }).catch(error => error);
      setTimeout(() => {
        console.log("Successfully uploaded !!");
       
      }, 10000);
    } else {
      
        
    }


  }





}
