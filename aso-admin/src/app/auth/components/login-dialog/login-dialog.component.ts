import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface Data{
  correo:string;
  celular:string;
}
@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css']
})
export class LoginDialogComponent {

  constructor(public dialogRef:MatDialogRef<LoginDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data:Data){}

  cerrar(): void{
    this.dialogRef.close();
  }
}
