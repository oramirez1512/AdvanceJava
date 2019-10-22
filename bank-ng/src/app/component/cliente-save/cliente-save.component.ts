import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/domain/cliente';
import { TipoDocumento } from 'src/app/domain/tipo-documento';
import { Activo } from 'src/app/domain/activo';
import { ClienteService } from 'src/app/service/cliente.service';
import { TipoDocumentoService } from 'src/app/service/tipo-documento.service';
import { ActivoService } from 'src/app/service/activo.service';

@Component({
  selector: 'app-cliente-save',
  templateUrl: './cliente-save.component.html',
  styleUrls: ['./cliente-save.component.css']
})
export class ClienteSaveComponent implements OnInit {

  public cliente:Cliente;
  public listaTipoDocumento:TipoDocumento[];
  public listaActivo:Activo[];

  public showMsg:boolean=false;
  public msg:string;

  constructor(public clienteService:ClienteService,
              public tipoDocumentoService:TipoDocumentoService,
              public activoService:ActivoService) { }

  ngOnInit() {
    this.cliente=new Cliente(0,"S","","","","",1);
    this.findAllActivo();
    this.findAllTipoDocumento();
  }

  public findAllTipoDocumento(){
    this.tipoDocumentoService.findAll().subscribe(data=>{
      this.listaTipoDocumento=data;
    });
  }
  public findAllActivo(){
    this.listaActivo=this.activoService.findAll();
  }

  public save(){
    this.clienteService.save(this.cliente).subscribe(data=>{
      this.showMsg=true;
      this.msg='El cliente se grabo con exito';
    },error=>{
      this.showMsg=true;
      this.msg=error.error.message;
    });
  }
}
