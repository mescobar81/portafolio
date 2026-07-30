export interface ICatastroTarjeta {
    process_id:   string;
    urlRedirect: string;
    mensaje:     string;
    status:      string;
}

export interface ITarjetaRecuperada {
    tarjetas: any[];
    mensaje:  string;
    status:   string;
}

export interface ITarjetaEliminada {
    mensaje: string;
    status:  string;
}
