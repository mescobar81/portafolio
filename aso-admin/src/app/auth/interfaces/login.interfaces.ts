/* export interface ILogin {
    recordarSesion:     boolean;
    usuario:            Usuario;
    parametrosGlobales: ParametrosGlobales;
}

export interface ParametrosGlobales {
    appVersionActualPlayStore?: number;
    estado?:                    boolean;
    importeMinimoOrden?:        number;
    importeMinimoPrestamo?:     number;
    requiereUpdate?:            boolean;
    topicos?:                   any[];
}

export interface Usuario {
    nroSocio?:           string;
    valido?:             boolean;
    documento?:          number;
    mensaje?:            string;
    nombre?:             string;
    solicitarNuevoPass?: string;
    rol?:                Rol;
    codaso?:             string;
    status?:             string;
}

export interface Rol {
    roles: string[];
} */

export interface ILogin {
    recordarSesion?:     boolean;
    usuario?:            Usuario;
    parametrosGlobales?: ParametrosGlobales;
}

export interface ParametrosGlobales {
    appVersionActualPlayStore?: number;
    estado?:                    boolean;
    importeMinimoOrden?:        number;
    importeMinimoPrestamo?:     number;
    requiereUpdate?:            boolean;
    topicos?:                   any[];
}

export interface Usuario {
    Correo?:             string;
    Celular?:            string;
    nroSocio?:           string;
    valido?:             boolean;
    documento?:          number;
    mensaje?:            string;
    nombre?:             string;
    solicitarNuevoPass?: string;
    rol?:                Rol;
    codaso?:             string;
    status?:             string;
}

export interface Rol {
    roles?: string[];
}

export interface IDatoAdicionalUsuario {
    nroSocio?: number;
    mensaje?:  string;
    status?:   string;
}
