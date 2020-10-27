package com.dosdeemetres.ayashome.Fragments.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dosdeemetres.ayashome.Clases.Reserva;
import com.dosdeemetres.ayashome.R;

import java.util.ArrayList;

/*  creamos el adapter para manejar el recyclerView de reservas
    hereda de RecyclerView.Adapter y de la clase interna ReservaBasicViewHolder (que tenemos que crear)
 */
public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaBasicViewHolder>{

    // propiedades
    private ArrayList<Reserva> listaReservas;
    // para diferenciar items pares e impares
    private static final int PAR = 0;
    private static final int IMPAR = 1;

    // Clase necesaria para adaptar los elementos de la lista a los elementos del RecyclerView
    public static class ReservaBasicViewHolder extends RecyclerView.ViewHolder{
        public TextView tvUsuario;
        public TextView tvFecha;
        public TextView tvHora;

        // constructor para el holder, creando vistas (para layouts) y enviarles datos
        // (binding --> onBindViewHolder)
        public ReservaBasicViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsuario = itemView.findViewById(R.id.tvUsuario);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvHora = itemView.findViewById(R.id.tvHora);

        }
    }

    // Constructor
    public ReservaAdapter(ArrayList <Reserva> listaReservas) { this.listaReservas = listaReservas;}

    // llamado cuando se necesita un viewholder, alimentando nuevos items cuando se necesitan
    @NonNull
    @Override
    public ReservaBasicViewHolder onCreateViewHolder (final ViewGroup parent, int viewType){

        View itemView;

        /*if (viewType == PAR) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_reserva_item, parent, false);
        }else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list_odd, parent, false);
        }*/

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_reserva_item_impar, parent, false);
        return new ReservaBasicViewHolder(itemView);
    }

    // Diferenciar entre elemento par e impar
    @Override
    public int getItemViewType (int position){

        if (position%2==0){
            return PAR;
        }else {
            return IMPAR;
        }
    }


    // refresca elementos en el recyclerView
    @Override
    public void onBindViewHolder(@NonNull ReservaBasicViewHolder holder, int position) {
        holder.tvUsuario.setText(listaReservas.get(position).getUsuario());
        holder.tvFecha.setText(listaReservas.get(position).getFecha());
        holder.tvHora.setText(listaReservas.get(position).getHora());
    }

    // maneja el final de la lista
    @Override
    public int getItemCount() {
        return listaReservas.size();
    }


}
