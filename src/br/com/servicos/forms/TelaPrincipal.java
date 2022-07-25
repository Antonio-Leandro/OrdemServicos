package br.com.servicos.forms;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class TelaPrincipal extends javax.swing.JFrame {


    public TelaPrincipal() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        LblLogo = new javax.swing.JLabel();
        LblUsuario = new javax.swing.JLabel();
        LblData = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        MenuCadatro = new javax.swing.JMenu();
        MenuItemCliente = new javax.swing.JMenuItem();
        MenuItemOS = new javax.swing.JMenuItem();
        MenuItemUsuarios = new javax.swing.JMenuItem();
        MenuRelatorio = new javax.swing.JMenu();
        MenuItemServicos = new javax.swing.JMenuItem();
        MenuItemClientes = new javax.swing.JMenuItem();
        MenuFinanceiro = new javax.swing.JMenu();
        MenuOpcoes = new javax.swing.JMenu();
        MenuItemSair = new javax.swing.JMenuItem();
        MenuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema para Controle de Serviços");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        LblLogo.setText("Logo");

        LblUsuario.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LblUsuario.setText("Usuário");

        LblData.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LblData.setText("Data");

        MenuCadatro.setText("Cadastro");

        MenuItemCliente.setText("Clientes");
        MenuItemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemClienteActionPerformed(evt);
            }
        });
        MenuCadatro.add(MenuItemCliente);

        MenuItemOS.setText("OS");
        MenuItemOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemOSActionPerformed(evt);
            }
        });
        MenuCadatro.add(MenuItemOS);

        MenuItemUsuarios.setText("Usuários");
        MenuItemUsuarios.setEnabled(false);
        MenuItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemUsuariosActionPerformed(evt);
            }
        });
        MenuCadatro.add(MenuItemUsuarios);

        Menu.add(MenuCadatro);

        MenuRelatorio.setText("Relatório");
        MenuRelatorio.setEnabled(false);

        MenuItemServicos.setText("Serviços");
        MenuRelatorio.add(MenuItemServicos);

        MenuItemClientes.setText("Clientes");
        MenuRelatorio.add(MenuItemClientes);

        Menu.add(MenuRelatorio);

        MenuFinanceiro.setText("Financeiro");
        Menu.add(MenuFinanceiro);

        MenuOpcoes.setText("Opções");
        MenuOpcoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpcoesActionPerformed(evt);
            }
        });

        MenuItemSair.setText("Sair");
        MenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSairActionPerformed(evt);
            }
        });
        MenuOpcoes.add(MenuItemSair);

        MenuItemSobre.setText("Sobre");
        MenuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSobreActionPerformed(evt);
            }
        });
        MenuOpcoes.add(MenuItemSobre);

        Menu.add(MenuOpcoes);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(LblLogo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblData)
                            .addComponent(LblUsuario))))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(LblUsuario)
                .addGap(28, 28, 28)
                .addComponent(LblData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addComponent(LblLogo)
                .addGap(146, 146, 146))
            .addComponent(Desktop)
        );

        setSize(new java.awt.Dimension(819, 538));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        LblData.setText(formatador.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void MenuOpcoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpcoesActionPerformed
        
    }//GEN-LAST:event_MenuOpcoesActionPerformed

    private void MenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSairActionPerformed
        int sair = JOptionPane.showConfirmDialog(null, "Deseja mesmo sair?", "Atencão", JOptionPane.YES_NO_OPTION);
        if(sair == JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_MenuItemSairActionPerformed

    private void MenuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSobreActionPerformed
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_MenuItemSobreActionPerformed

    private void MenuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemUsuariosActionPerformed
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        Desktop.add(usuario);
    }//GEN-LAST:event_MenuItemUsuariosActionPerformed

    private void MenuItemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemClienteActionPerformed
        TelaDeClientes clientes = new TelaDeClientes();
        clientes.setVisible(true);
        Desktop.add(clientes);
    }//GEN-LAST:event_MenuItemClienteActionPerformed

    private void MenuItemOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemOSActionPerformed
        TelaOS os = new TelaOS();
        os.setVisible(true);
        Desktop.add(os);
    }//GEN-LAST:event_MenuItemOSActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JLabel LblData;
    private javax.swing.JLabel LblLogo;
    public static javax.swing.JLabel LblUsuario;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu MenuCadatro;
    private javax.swing.JMenu MenuFinanceiro;
    private javax.swing.JMenuItem MenuItemCliente;
    private javax.swing.JMenuItem MenuItemClientes;
    private javax.swing.JMenuItem MenuItemOS;
    private javax.swing.JMenuItem MenuItemSair;
    private javax.swing.JMenuItem MenuItemServicos;
    private javax.swing.JMenuItem MenuItemSobre;
    public static javax.swing.JMenuItem MenuItemUsuarios;
    private javax.swing.JMenu MenuOpcoes;
    public static javax.swing.JMenu MenuRelatorio;
    // End of variables declaration//GEN-END:variables
}
