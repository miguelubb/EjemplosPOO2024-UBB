package ejemplos.gui.edad;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class BirthdaySong {
    static Thread thread = null;

    private static void song(){
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            try {
                // Crear un sintetizador MIDI

                // Obtener los canales MIDI (típicamente hay 16)
                MidiChannel[] channels = synth.getChannels();
                MidiChannel channel = channels[0]; // Usaremos el canal 0

                // Configurar un instrumento (0 = Piano acústico)
                //1 = piano brillante
                channel.programChange(1);

                // Notas de la canción "Feliz Cumpleaños"
                int[] notes = {
                        60, 60, 62, 60, 65, 64, // "Cumpleaños fe-"
                        60, 60, 62, 60, 67, 65, // "liz, te dese-"
                        60, 60, 72, 69, 65, 64, 62, // "amos a ti"
                        70, 70, 69, 65, 67, 65 // "¡Feliz cumpleaños!"
                };

                // Duraciones correspondientes para cada nota (en milisegundos)
                int[] duration = {
                        500, 250, 600, 600, 600, 1200,  // "Cumpleaños fe-"
                        600, 250, 600, 600, 600, 1200,  // "liz, te dese-"
                        600, 250, 600, 600, 600, 600, 1200, // "amos a ti"
                        600, 250, 600, 600, 600, 1200   // "¡Feliz cumpleaños!"
                };
                // Verificar que las longitudes de los arreglos coincidan
                if (notes.length != duration.length) {
                    System.err.println("Error: La longitud de los arreglos de notas y duraciones no coincide.");
                    return;
                }
                // Activar el pedal de sustain antes de reproducir la canción
                //channel.controlChange(64, 127); // Activar sustain
                //le damos tiempo al secuenciador de cargar en memoria
                //Thread.sleep(2000);
                // Reproducir la canción
                for (int j = 0; j <= 20; j += 10) {
                    int i = 0;
                    for (int note : notes) {
                        if (note != 0) {
                            channel.noteOn(note, 100); // Iniciar la nota
                        }
                        Thread.sleep(duration[i++] - j);   // Esperar la duración específica de la nota
                        channel.noteOff(note);        // Detener la nota
                    }
                    // Configurar un instrumento (0 = Piano acústico)
                    channel.programChange(56 + j); //trompeta, 65 saxofón alto
                }

                // Desactivar el pedal de sustain después de la canción
                channel.controlChange(64, 0); // Desactivar sustain

                // Cerrar el sintetizador
                synth.close();

            } catch (Exception e) {
                //si ocurre una excepción distinta a las causadas por abrir el sintetizador, hay que cerrar.
                e.printStackTrace();
                synth.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void play() {
        if (thread != null) {
            thread.interrupt();
        }
        thread = new Thread(BirthdaySong::song);
        thread.start();
    }

    public static void main(String[] args) {
        play();
    }
}

