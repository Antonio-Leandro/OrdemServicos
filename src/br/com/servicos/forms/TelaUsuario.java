package br.com.servicos.forms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.servicos.dao.ModuloConexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void consulta() {
        String sql = "select * from tbl_usuario where id_usuario=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNome.setText(rs.getString("usuario"));
                txtCelular.setText(rs.getString("celular"));
                txtLogin.setText(rs.getString("login"));
                passSenha.setText(rs.getString("senha"));
                comboPerfil.setSelectedItem(rs.getString("perfil"));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                txtNome.setText("");
                txtCelular.setText("");
                txtLogin.setText("");
                passSenha.setText("");
                comboPerfil.setSelectedItem(null);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void salvar() {
        String sql = "insert tbl_usuario(id_usuario,usuario,celular,login,senha,perfil)"
                + "values(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            pst.setString(2, txtNome.getText());
            pst.setString(3, txtCelular.getText());
            pst.setString(4, txtLogin.getText());
            pst.setString(5, passSenha.getText());
            pst.setString(6, comboPerfil.getSelectedItem().toString());

            if ((txtId.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtLogin.getText().isEmpty()) || (passSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "preencha todos os campos obrigatórios!");
            } else {

                int salvo = pst.executeUpdate();
                if (salvo > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastro Efetuado com sucesso!");
                    txtId.setText("");
                    txtNome.setText("");
                    txtCelular.setText("");
                    txtLogin.setText("");
                    passSenha.setText("");
                    comboPerfil.setSelectedItem(null);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar os dados!" + ex.getMessage());
        }
    }

    private void alterar() {
        String sql = "update tbl_usuario set usuario=?,celular=?,login=?,senha=?,perfil=?"
                + "where id_usuario=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCelular.getText());
            pst.setString(3, txtLogin.getText());
            pst.setString(4, passSenha.getText());
            pst.setString(5, comboPerfil.getSelectedItem().toString());
            pst.setString(6, txtId.getText());

            if ((txtId.getText().isEmpty()) || (txtNome.getText().isEmpty()) || (txtLogin.getText().isEmpty()) || (passSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "preencha todos os campos obrigatórios!");
            } else {

                int salvo = pst.executeUpdate();
                if (salvo > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!");
                    txtId.setText("");
                    txtNome.setText("");
                    txtCelular.setText("");
                    txtLogin.setText("");
                    passSenha.setText("");
                    comboPerfil.setSelectedItem(null);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel atualisar o cadastro!" + ex.getMessage());
        }
    }

    private void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Exclusão?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbl_usuario where id_usuario=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastro removido com sucesso!");
                    txtId.setText("");
                    txtNome.setText("");
                    txtCelular.setText("");
                    txtLogin.setText("");
                    passSenha.setText("");
                    comboPerfil.setSelectedItem(null);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possivel deletar o cadastro!" + ex.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblId = new javax.swing.JLabel();
        LblNome = new javax.swing.JLabel();
        LblCelular = new javax.swing.JLabel();
        LblLogin = new javax.swing.JLabel();
        LblSenha = new javax.swing.JLabel();
        LblPerfil = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        passSenha = new javax.swing.JPasswordField();
        comboPerfil = new javax.swing.JComboBox<>();
        BtnSalvar = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnConsultar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(555, 490));

        LblId.setText("*id");

        LblNome.setText("*Nome");

        LblCelular.setText("Celular");

        LblLogin.setText("*Login");

        LblSenha.setText("*Senha");

        LblPerfil.setText("*Perfil");

        comboPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "User", "Admin" }));

        BtnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/add_user1_.png"))); // NOI18N
        BtnSalvar.setToolTipText("Salvar");
        BtnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnSalvar.setPreferredSize(new java.awt.Dimension(50, 50));
        BtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarActionPerformed(evt);
            }
        });

        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/edit_user1_.png"))); // NOI18N
        BtnEditar.setToolTipText("Editar");
        BtnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEditar.setPreferredSize(new java.awt.Dimension(50, 50));
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });

        BtnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/busca_user1_.png"))); // NOI18N
        BtnConsultar.setToolTipText("Consultar");
        BtnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnConsultar.setPreferredSize(new java.awt.Dimension(50, 50));
        BtnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConsultarActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/del_user1_.png"))); // NOI18N
        jButton4.setToolTipText("Deletar");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setPreferredSize(new java.awt.Dimension(50, 50));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("*Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(BtnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 184, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(LblId)
                                .addComponent(LblCelular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LblNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(LblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                    .addComponent(txtCelular))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LblPerfil, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LblSenha, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(passSenha)
                                    .addComponent(comboPerfil, 0, 113, Short.MAX_VALUE))))))
                .addGap(52, 52, 52))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BtnConsultar, BtnEditar, BtnSalvar, jButton4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblCelular)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblPerfil)
                    .addComponent(comboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblLogin)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblSenha)
                    .addComponent(passSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        setBounds(0, 0, 553, 475);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConsultarActionPerformed
        consulta();
    }//GEN-LAST:event_BtnConsultarActionPerformed

    private void BtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_BtnSalvarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        alterar();
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        deletar();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConsultar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnSalvar;
    private javax.swing.JLabel LblCelular;
    private javax.swing.JLabel LblId;
    private javax.swing.JLabel LblLogin;
    private javax.swing.JLabel LblNome;
    private javax.swing.JLabel LblPerfil;
    private javax.swing.JLabel LblSenha;
    private javax.swing.JComboBox<String> comboPerfil;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField passSenha;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
