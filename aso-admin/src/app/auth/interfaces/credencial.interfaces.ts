export interface ICredencial {
    documento?:      string;
    clave?:          string;
    recordarSesion?: boolean;
    device?:         Device;
    notificacion?:   Notificacion;
}

export interface Device {
    os:      string;
    version: string;
    model:   string;
    ip:      string;
}

export interface Notificacion {
    idTokenFirebase: string;
}
