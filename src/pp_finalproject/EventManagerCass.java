/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_finalproject;

import estgconstroi.Event;
import estgconstroi.EventManager;
import estgconstroi.Notifier;
import estgconstroi.enums.EventPriority;
import estgconstroi.exceptions.EventManagerException;
import java.time.LocalDate;

/**
 *
 * @author Utilizador
 */
public class EventManagerCass implements EventManager {

    private NotifierClass[] notifie;
    private Event[] event;
    private int numberNotifies;
    private int numberEvents;

    public EventManagerCass() {
        this.numberNotifies = 0;
        this.numberEvents = 0;
        this.notifie = new NotifierClass[0];
        this.event = new Event[0];
    }

    private void checkNotifierSize() {
        if (this.notifie.length == numberNotifies) {
            NotifierClass[] newarray = new NotifierClass[numberNotifies];
            for (int i = 0; i < numberNotifies; i++) {
                newarray[i] = this.notifie[i];
            }
            this.notifie = new NotifierClass[numberNotifies + 10];
            for (int i = 0; i < numberNotifies; i++) {
                this.notifie[i] = newarray[i];
            }
        }
    }

    private void checkEventSize() {
        if (this.event.length == numberEvents) {
            Event[] newarray = new Event[numberEvents];
            for (int i = 0; i < numberEvents; i++) {
                newarray[i] = this.event[i];
            }
            this.event = new Event[numberEvents + 10];
            for (int i = 0; i < numberEvents; i++) {
                this.event[i] = newarray[i];
            }
        }
    }

    @Override
    public void addNotifier(Notifier ntfr) throws EventManagerException {
        try {
            checkNotifierSize();
            this.notifie[this.numberNotifies++] = (NotifierClass) ntfr;
            System.out.println("Notificação adicionada");
        } catch (Exception e) {
            throw new EventManagerException("Algo de errado aconteceu ao adicionar a Notificação");
        }
    }

    @Override
    public void removeNotifier(Notifier ntfr) throws EventManagerException {
        try {
            boolean equal = false;
            int posicaoarray = -1;
            for (int i = 0; i < this.numberNotifies; i++) {
                if (ntfr.equals(this.notifie[i]) == true) {
                    equal = true;
                    posicaoarray = i;
                }
            }
            if (equal == true) {
                NotifierClass[] array = new NotifierClass[this.notifie.length - 1]; //crio um novo array com menos uma posicao
                int k = 0;//variavel que vai incrementar 
                for (int i = 0; i < this.notifie.length; i++) {//enquanto o i for menor que o tamanho do array
                    if (i != posicaoarray) { // e o i for diferente da posicao recebida
                        array[k] = this.notifie[i];//o array guarda o que tá no array original
                        k++;
                    }
                }
                this.notifie = array; //depois copio a cópia para o original
                this.numberNotifies--;
                System.out.println("Trabalhador foi removido com sucesso");
            }
        } catch (Exception e) {
            throw new EventManagerException("Erro ao remover o trabalhador");
        }
    }

    @Override
    public void reportEvent(Event event) throws EventManagerException {
        try {
            checkEventSize();
            this.event[this.numberEvents++] = event;
            System.out.println("Evento reportado com sucesso");
            //meter a notificar aqui também

        } catch (Exception e) {
            throw new EventManagerException("Erro ao fazer o report do evento");
        }
    }

    @Override
    public void removeAllEvents() {
        Event[] array = new Event[0];
        this.event = array;
        System.out.println("Todos os eventos foram removidos com sucesso");
    }

    @Override
    public void removeEvent(Event event) throws EventManagerException {
        try {
            boolean equal = false;
            int posicaoarray = -1;
            for (int i = 0; i < this.numberEvents; i++) {
                if (event.equals(this.event[i]) == true) {
                    equal = true;
                    posicaoarray = i;
                }
            }
            if (equal == true) {
                Event[] array = new Event[this.event.length - 1]; //crio um novo array com menos uma posicao
                int k = 0;//variavel que vai incrementar 
                for (int i = 0; i < this.event.length; i++) {//enquanto o i for menor que o tamanho do array
                    if (i != posicaoarray) { // e o i for diferente da posicao recebida
                        array[k] = this.event[i];//o array guarda o que tá no array original
                        k++;
                    }
                }
                this.event = array; //depois copio a cópia para o original
                this.numberEvents--;
                System.out.println("Evento removido com sucesso");
            }
        } catch (Exception e) {
            throw new EventManagerException("Erro a remover o evento");
        }
    }

    @Override
    public Event[] getEvent(EventPriority ep) {
        int contador = 0;
        for (int i = 0; i < this.numberEvents; i++) {
            if (ep == (this.event[i].getPriority())) {
                contador++;
            }
        }

        Event[] priorityarray = new Event[contador];
        contador = 0;

        for (int i = 0; i < this.numberEvents; i++) {
            if (ep == (this.event[i].getPriority())) {
                priorityarray[contador] = this.event[i];
                contador++;
            }
        }
        return priorityarray;
    }

    @Override
    public Event[] getEvent(Class type) {
        int contador = 0;
        for (int i = 0; i < this.numberEvents; i++) {
            if (type == (this.event[i].getClass())) {
                contador++;
            }
        }

        Event[] array = new Event[contador];
        contador = 0;

        for (int i = 0; i < this.numberEvents; i++) {
            if (type == (this.event[i].getClass())) {
                array[contador] = this.event[i];
                contador++;
            }
        }
        return array;
    }

    @Override
    public Event[] getEvent(LocalDate ld) {
        int contador = 0;
        for (int i = 0; i < this.numberEvents; i++) {
            if (ld == (this.event[i].getDate())) {
                contador++;
            }
        }
        Event[] array = new Event[contador];
        contador = 0;

        for (int i = 0; i < this.numberEvents; i++) {
            if (ld == (this.event[i].getDate())) {
                array[contador] = this.event[i];
                contador++;
            }
        }
        return array;
    }

    @Override
    public Event[] getEvent(LocalDate ld, LocalDate ld1) {
        int contador = 0;
        for (int i = 0; i < this.numberEvents; i++) {
            if (ld.compareTo(this.event[i].getDate()) >= 0 && ld1.compareTo(this.event[i].getDate()) <= 0) {
                contador++;
            }
        }

        Event[] array = new Event[contador];
        contador = 0;

        for (int i = 0; i < this.numberEvents; i++) {
            if (ld.compareTo(this.event[i].getDate()) >= 0 && ld1.compareTo(this.event[i].getDate()) <= 0) {
                array[contador] = this.event[i];
                contador++;
            }
        }
        return array;
    }

}
