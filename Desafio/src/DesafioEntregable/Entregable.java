package DesafioEntregable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Entregable {

    // =========================
    // CLASE PERSONA
    // =========================
    static abstract class Persona {

        private final String nombre;
        private final String dni;
        private final String telefono;

        public Persona(String nombre, String dni, String telefono) {
            this.nombre = nombre;
            this.dni = dni;
            this.telefono = telefono;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDni() {
            return dni;
        }

        public String getTelefono() {
            return telefono;
        }

        public void mostrarDatos() {
            System.out.println("Nombre: " + nombre);
            System.out.println("DNI: " + dni);
            System.out.println("Telefono: " + telefono);
        }
    }

    // =========================
    // CLASE PACIENTE
    // =========================
    static class Paciente extends Persona {

        private final HistoriaClinica historiaClinica;

        public Paciente(String nombre, String dni, String telefono) {
            super(nombre, dni, telefono);
            this.historiaClinica = new HistoriaClinica();
        }

        public HistoriaClinica getHistoriaClinica() {
            return historiaClinica;
        }
    }

    // =========================
    // CLASE MEDICO
    // =========================
    static class Medico extends Persona {

        private final String especialidad;
        private final String colegiatura;

        public Medico(
                String nombre,
                String dni,
                String telefono,
                String especialidad,
                String colegiatura) {

            super(nombre, dni, telefono);
            this.especialidad = especialidad;
            this.colegiatura = colegiatura;
        }

        public String getEspecialidad() {
            return especialidad;
        }

        public String getColegiatura() {
            return colegiatura;
        }

        public void atenderPaciente(Paciente paciente) {
            System.out.println(
                    "El medico " + getNombre()
                    + " esta atendiendo al paciente "
                    + paciente.getNombre()
            );
        }
    }

    // =========================
    // CLASE ATENCION MEDICA
    // =========================
    static class AtencionMedica {

        private final LocalDate fecha;
        private final String diagnostico;
        private final String tratamiento;
        private final String observaciones;

        public AtencionMedica(
                LocalDate fecha,
                String diagnostico,
                String tratamiento,
                String observaciones) {

            this.fecha = fecha;
            this.diagnostico = diagnostico;
            this.tratamiento = tratamiento;
            this.observaciones = observaciones;
        }

        public void mostrarAtencion() {
            System.out.println("Fecha: " + fecha);
            System.out.println("Diagnostico: " + diagnostico);
            System.out.println("Tratamiento: " + tratamiento);
            System.out.println("Observaciones: " + observaciones);
        }
    }

    // =========================
    // CLASE HISTORIA CLINICA
    // =========================
    static class HistoriaClinica {

        private final List<AtencionMedica> atenciones;

        public HistoriaClinica() {
            atenciones = new ArrayList<>();
        }

        public void agregarAtencion(AtencionMedica atencion) {
            atenciones.add(atencion);
        }

        public void mostrarHistoria() {

            if (atenciones.isEmpty()) {
                System.out.println("No existen atenciones registradas.");
                return;
            }

            for (AtencionMedica atencion : atenciones) {
                atencion.mostrarAtencion();
                System.out.println("-----------------------------");
            }
        }
    }

    // =========================
    // CLASE CITA
    // =========================
    static class Cita {

        private final Paciente paciente;
        private final Medico medico;
        private final LocalDate fecha;
        private String estado;

        public Cita(
                Paciente paciente,
                Medico medico,
                LocalDate fecha) {

            this.paciente = paciente;
            this.medico = medico;
            this.fecha = fecha;
            this.estado = "Pendiente";
        }

        public void confirmar() {
            estado = "Confirmada";
        }

        public void cancelar() {
            estado = "Cancelada";
        }

        public String getEstado() {
            return estado;
        }

        public LocalDate getFecha() {
            return fecha;
        }

        public Paciente getPaciente() {
            return paciente;
        }

        public Medico getMedico() {
            return medico;
        }

        public void mostrarCita() {
            System.out.println("Paciente: " + paciente.getNombre());
            System.out.println("Medico: " + medico.getNombre());
            System.out.println("Especialidad: " + medico.getEspecialidad());
            System.out.println("Fecha: " + fecha);
            System.out.println("Estado: " + estado);
        }
    }

    // =========================
    // CLASE REPORTE
    // =========================
    static class Reporte {

        public static void mostrarCitasConfirmadas(List<Cita> citas) {

            List<Cita> confirmadas = citas.stream()
                    .filter(cita -> cita.getEstado().equals("Confirmada"))
                    .collect(Collectors.toList());

            System.out.println(
                    "Cantidad de citas confirmadas: " + confirmadas.size()
            );

            for (Cita cita : confirmadas) {
                cita.mostrarCita();
                System.out.println("-----------------------------");
            }
        }
    }

    // =========================
    // SINGLETON SISTEMA SALUD
    // =========================
    static class SistemaSalud {

        private static SistemaSalud instancia;

        private final List<Paciente> pacientes;
        private final List<Medico> medicos;
        private final List<Cita> citas;

        private SistemaSalud() {
            pacientes = new ArrayList<>();
            medicos = new ArrayList<>();
            citas = new ArrayList<>();
        }

        public static SistemaSalud obtenerInstancia() {
            if (instancia == null) {
                instancia = new SistemaSalud();
            }
            return instancia;
        }

        public void registrarPaciente(Paciente paciente) {
            pacientes.add(paciente);
        }

        public void registrarMedico(Medico medico) {
            medicos.add(medico);
        }

        public void registrarCita(Cita cita) {
            citas.add(cita);
        }

        public List<Cita> getCitas() {
            return citas;
        }
    }

    // =========================
    // MÉTODO PRINCIPAL
    // =========================
    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println(" SISTEMA DE GESTION - PUESTO DE SALUD ÑAÑA");
        System.out.println("==========================================");

        // Crear sistema
        SistemaSalud sistema = SistemaSalud.obtenerInstancia();

        // Crear paciente
        Paciente paciente = new Paciente(
                "Carlos Ramirez",
                "74561238",
                "987654321"
        );

        // Crear medico
        Medico medico = new Medico(
                "Ana Torres",
                "45678912",
                "912345678",
                "Medicina General",
                "CMP-45821"
        );

        // Registrar paciente y medico
        sistema.registrarPaciente(paciente);
        sistema.registrarMedico(medico);

        // Crear cita
        Cita cita = new Cita(
                paciente,
                medico,
                LocalDate.of(2026, 9, 20)
        );

        // Registrar cita
        sistema.registrarCita(cita);

        // Confirmar cita
        cita.confirmar();

        System.out.println("\n========== DATOS DEL PACIENTE ==========");
        paciente.mostrarDatos();

        System.out.println("\n========== DATOS DEL MEDICO ==========");
        medico.mostrarDatos();
        System.out.println("Especialidad: " + medico.getEspecialidad());
        System.out.println("Colegiatura: " + medico.getColegiatura());

        System.out.println("\n========== CITA ==========");
        cita.mostrarCita();

        // Registrar atención médica
        AtencionMedica atencion = new AtencionMedica(
                LocalDate.now(),
                "Infeccion respiratoria leve",
                "Reposo y tratamiento indicado",
                "Control en 7 dias"
        );

        paciente.getHistoriaClinica().agregarAtencion(atencion);

        System.out.println("\n========== HISTORIA CLINICA ==========");
        paciente.getHistoriaClinica().mostrarHistoria();

        // Reporte de citas
        System.out.println("\n========== REPORTE ==========");
        Reporte.mostrarCitasConfirmadas(sistema.getCitas());

        System.out.println("\n==========================================");
        System.out.println(" PROGRAMA EJECUTADO CORRECTAMENTE");
        System.out.println("==========================================");
    }
}