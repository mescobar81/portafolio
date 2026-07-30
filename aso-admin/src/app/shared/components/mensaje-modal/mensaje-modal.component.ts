import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mensaje-modal',
  templateUrl: './mensaje-modal.component.html',
  styleUrls: ['./mensaje-modal.component.css']
})
export class MensajeModalComponent {

  constructor(private route:ActivatedRoute,
              private router:Router){}

  ngAfterViewInit() {
    this.route.queryParams.subscribe(params => {
      const {status, description} = params;
      if(status === 'add_new_card_success'){
        Swal.fire({
          icon: 'success',
          title: 'Catastro Tarjeta',
          text: 'Tarjeta agregada correctamente'
        });
      }else{
        Swal.fire({
          icon: 'error',
          title: 'Catastro Tarjeta',
          text: description
        }); 
      }
      this.router.navigateByUrl('/menu/view-tarjeta');
    });
  }
}
