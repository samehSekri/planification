import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../../authentication';
import { PolyvalenceFichierService } from './polyvalence-fichier.service';

@Component({
  selector: 'app-polyvalence-fichier',
  templateUrl: './polyvalence-fichier.component.html',
  styleUrls: ['./polyvalence-fichier.component.scss']
})
export class PolyvalenceFichierComponent implements OnInit {

  constructor(private auth: AuthenticationService,
              private polyvalenceService: PolyvalenceFichierService) {  }
 

  ngOnInit() {
  }
excelFileChange(event) {
  const fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            const file = event.target.files.item(0)
            const formData = new FormData();
            formData.append('file', file);
            this.polyvalenceService.uploadExcel(formData).subscribe(val => {
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



  upload(){
    
  }
}
