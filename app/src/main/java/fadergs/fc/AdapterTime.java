package fadergs.fc;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterTime extends BaseAdapter {

    private List<Time> listaTimes;
    private Context context;
    private LayoutInflater inflater;

    public AdapterTime(Context context, List<Time> listaTimes){
        this.context = context;
        this.listaTimes = listaTimes;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return listaTimes.size();
    }

    @Override
    public Object getItem(int i) {
        return listaTimes.get( i );
    }

    @Override
    public long getItemId(int i) {
        return listaTimes.get( i ).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemSuporte item;

        if ( view == null){
            view = inflater.inflate(R.layout.activity_lista_times, null);
            item = new ItemSuporte();
            item.tvNome = view.findViewById(R.id.activityFormTimesNome);
            item.layout = view.findViewById(R.id.linearLayout);
            view.setTag( item );
        }else {
            item = (ItemSuporte) view.getTag();
        }

        Time time = listaTimes.get( i );
        item.tvId.setText( String.valueOf( time.getId() ) );
        item.tvNome.setText( time.getNome()  );

        if ( time.getNome().equals( "Lista Vazia!" )){
            item.tvId.setText( " " );

        }

        if ( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.WHITE);
        }else {
            item.layout.setBackgroundColor( Color.rgb(230, 230, 230) );
        }


        return view;
    }


    private class ItemSuporte{
        TextView tvId, tvNome;
        LinearLayout layout;
    }

}