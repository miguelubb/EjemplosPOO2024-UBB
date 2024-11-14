package ejemplos.gui.edad;


import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MidiPlayer {

    private Sequencer sequencer;

    public MidiPlayer(String midiFilePath) {
        try {
            // Obtener el secuenciador y abrirlo
            sequencer = MidiSystem.getSequencer();
            sequencer.open();

            // Cargar el archivo MIDI
            File midiFile = new File(midiFilePath);
            Sequence sequence = MidiSystem.getSequence(midiFile);

            // Configurar el secuenciador con la secuencia
            sequencer.setSequence(sequence);



        } catch (MidiUnavailableException | InvalidMidiDataException | IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (sequencer != null && !sequencer.isRunning()) {
            sequencer.setTickPosition(0); // Reiniciar al inicio
            sequencer.start();
        }
    }

    public void stop() {
        if (sequencer != null && sequencer.isRunning()) {
            sequencer.stop();
        }
    }

    public void close() {
        if (sequencer != null && sequencer.isOpen()) {
            sequencer.close();
        }
    }

    public static void main(String[] args) {
        // Ruta al archivo MIDI descargado
        String midiFilePath = "src/ejemplos/gui/edad/canciones/cumple.mid";

        MidiPlayer player = new MidiPlayer(midiFilePath);
        player.play();

        // Esperar a que termine la reproducción
        while (player.sequencer.isRunning()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        player.close();
    }
}
