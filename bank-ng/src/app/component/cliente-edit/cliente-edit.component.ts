import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ClienteService } from 'src/app/service/cliente.service';
import { Cliente } from 'src/app/domain/cliente';
import { TipoDocumento } from 'src/app/domain/tipo-documento';
import { Activo } from 'src/app/domain/activo';
import { TipoDocumentoService } from 'src/app/service/tipo-documento.service';
import { ActivoService } from 'src/app/service/activo.service';

@Component({
  selector: 'app-cliente-edit',
  templateUrl: './cliente-edit.component.html',
  styleUrls: ['./cliente-edit.component.css']
})
export class ClienteEditComponent implements OnInit {

  public id: string;
  public cliente: Cliente;
  public listaTipoDocumento: TipoDocumento[];
  public listaActivo: Activo[];

  public showMsg: boolean = false;
  public msg: string;

  constructor(
    public router: Router,
    public activatedRoute: ActivatedRoute,
    public clienteService: ClienteService,
    public tipoDocumentoService: TipoDocumentoService,
    public activoService: ActivoService
  ) { }

  ngOnInit() {
    this.loadCliente();
    this.findAllActivo();
    this.findAllTipoDocumento();
  }

  public findAllTipoDocumento() {
    this.tipoDocumentoService.findAll().subscribe(data => {
      this.listaTipoDocumento = data;
    });
  }
  public findAllActivo() {
    this.listaActivo = this.activoService.findAll();
  }

  public loadCliente() {
    let params = this.activatedRoute.params['_value'];
    this.id = params.id;
    this.clienteService.findById(this.id).subscribe(data => {
      this.cliente = data;
    })
  }

  public update() {
    this.clienteService.update(this.cliente).subscribe(data => {
      this.showMsg = true;
      this.msg = 'El cliente se modificó con éxito';
    }, error => {
      this.showMsg = true;
      this.msg = error.error.message;
    })
  }

  public delete() {
    this.clienteService.delete(this.id).subscribe(data => {
      this.showMsg = true;
      this.msg = 'El cliente se borró con éxito';
    }, error => {
      this.showMsg = true;
      this.msg = error.error.message;
    })
  }
}
