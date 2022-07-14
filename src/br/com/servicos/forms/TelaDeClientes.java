package br.com.servicos.forms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.servicos.dao.ModuloConexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TelaDeClientes extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet re = null;

    public TelaDeClientes() {
        initComponents();
        //conexao = ModuloConexao.conector();
    }

    private void salvar() {
        conexao = ModuloConexao.conector();
        String sql = "insert into tbl_clientes(nomecli,estadocivilcli,cpfcli,rgcli,sexocli,endcli,numendcli,cepcli,cidadecli,estadocli,fonecli,emailcli)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, TxtNome.getText());
            pst.setString(2, ComboEstadoCivil.getSelectedItem().toString());
            pst.setString(3, FormatCpf.getText());
            pst.setString(4, FormatRg.getText());
            pst.setString(5, ComboSexo.getSelectedItem().toString());
            pst.setString(6, TxtEndereco.getText());
            pst.setString(7, JTxtNumero.getText());
            pst.setString(8, FormatCep.getText());
            pst.setString(9, TxtCidade.getText());
            pst.setString(10, ComboEstado.getSelectedItem().toString());
            pst.setString(11, FormatTelefone.getText());
            pst.setString(12, TxtEmail.getText());

            if ((TxtNome.getText().isEmpty()) || (FormatCpf.getText().isEmpty()) || (TxtEndereco.getText().isEmpty()) || (JTxtNumero.getText().isEmpty()) || (FormatCep.getText().isEmpty() || (TxtCidade.getText().isEmpty() || (ComboEstado.getSelectedItem().toString().isEmpty() || (FormatTelefone.getText().isEmpty()))))) {
                JOptionPane.showMessageDialog(null, "preencha todos os campos obrigatórios!");
            } else {
                int salvo = pst.executeUpdate();
                if (salvo > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastro Efetuado com sucesso!");
                    TxtNome.setText(null);
                    ComboEstadoCivil.setSelectedItem(null);
                    FormatCpf.setText(null);
                    FormatRg.setText(null);
                    ComboSexo.setSelectedItem(null);
                    TxtEndereco.setText(null);
                    JTxtNumero.setText(null);
                    FormatCep.setText(null);
                    TxtCidade.setText(null);
                    ComboEstado.setSelectedItem(null);
                    FormatTelefone.setText(null);
                    TxtEmail.setText(null);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar os dados!" + ex.getMessage());
        }
        try {
            pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel encerrar o Statement!" + ex.getMessage());
        }
        try {
            conexao.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel fechar a conexão!" + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        LblNome = new javax.swing.JLabel();
        LblEndereco = new javax.swing.JLabel();
        LblTelefone = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LblCpf = new javax.swing.JLabel();
        LblRg = new javax.swing.JLabel();
        ComboEstadoCivil = new javax.swing.JComboBox<>();
        FormatCpf = new javax.swing.JFormattedTextField();
        FormatRg = new javax.swing.JFormattedTextField();
        LblSexo = new javax.swing.JLabel();
        ComboSexo = new javax.swing.JComboBox<>();
        LblNumero = new javax.swing.JLabel();
        LblCep = new javax.swing.JLabel();
        LblCidade = new javax.swing.JLabel();
        LblEstado = new javax.swing.JLabel();
        LblEmail = new javax.swing.JLabel();
        TxtNome = new javax.swing.JTextField();
        TxtEndereco = new javax.swing.JTextField();
        TxtNumero = new javax.swing.JTextField();
        FormatCep = new javax.swing.JFormattedTextField();
        TxtCidade = new javax.swing.JTextField();
        FormatTelefone = new javax.swing.JFormattedTextField();
        TxtEmail = new javax.swing.JTextField();
        ComboEstado = new javax.swing.JComboBox<>();
        JTxtNumero = new javax.swing.JTextField();
        BtnSalvar = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnDeletar = new javax.swing.JButton();
        TxtPesquisaCli = new javax.swing.JTextField();
        LblPesquisaCli = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblClientes = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Tela de Clientes");
        setPreferredSize(new java.awt.Dimension(555, 490));

        jLabel1.setText("*Campos obrigatórios");

        LblNome.setText("* Nome");

        LblEndereco.setText("* Endereço");

        LblTelefone.setText("* Telefone");

        jLabel5.setText("Estado civil");

        LblCpf.setText("* CPF");

        LblRg.setText("RG");

        ComboEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Casado(a)", "Solteiro(a)", "União estável" }));

        try {
            FormatCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        LblSexo.setText("Sexo");

        ComboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Masculino", "Feminino" }));

        LblNumero.setText("* Número");

        LblCep.setText("* Cep");

        LblCidade.setText("* Cidade");

        LblEstado.setText("* Estado");

        LblEmail.setText("E-mail");

        try {
            FormatCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            FormatTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        ComboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "RO", "AC", "AM", "RR", "PA", "AP", "TO", "MA", "PI", "CE", "RN", "PB", "PE", "AL", "SE", "BA", "MG", "ES", "RJ", "SP", "PR", "SC", "RS", "MS", "MT", "GO", "DF" }));

        BtnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/add_user1_.png"))); // NOI18N
        BtnSalvar.setPreferredSize(new java.awt.Dimension(50, 50));
        BtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarActionPerformed(evt);
            }
        });

        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/edit_user1_.png"))); // NOI18N
        BtnEditar.setPreferredSize(new java.awt.Dimension(50, 50));
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });

        BtnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/del_user1_.png"))); // NOI18N
        BtnDeletar.setPreferredSize(new java.awt.Dimension(50, 50));

        LblPesquisaCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/Search_user.png"))); // NOI18N

        TblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TblClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TxtPesquisaCli, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblPesquisaCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(LblEmail))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(LblCep)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(16, 16, 16)
                                                        .addComponent(LblNome))
                                                    .addComponent(LblCpf, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(2, 2, 2)))
                                        .addComponent(LblTelefone, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(LblEndereco))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(BtnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(FormatCep, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LblCidade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(LblEstado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FormatTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(LblNumero))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JTxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(TxtNome)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(FormatCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(LblRg)
                                                .addGap(7, 7, 7)
                                                .addComponent(FormatRg, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(LblSexo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ComboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ComboEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TxtPesquisaCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LblPesquisaCli)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblNome)
                    .addComponent(jLabel5)
                    .addComponent(ComboEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblCpf)
                    .addComponent(LblRg)
                    .addComponent(FormatCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FormatRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblSexo)
                    .addComponent(ComboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblEndereco)
                    .addComponent(LblNumero)
                    .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblCep)
                    .addComponent(LblCidade)
                    .addComponent(LblEstado)
                    .addComponent(FormatCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FormatTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblTelefone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblEmail)
                    .addComponent(TxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        conexao = ModuloConexao.conector();
        String sql = "update tbl_clientes set id_cli=?,"
                + "nomecli=?,estadocivilcli=?,cpfcli=?,rgcli=?,sexocli=?,endcli=?,"
                + "numendcli=?,cepcli=?,cidadecli=?,estadocli=?,fonecli=?,emailcli"
                + "where ";
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_BtnSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnDeletar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnSalvar;
    private javax.swing.JComboBox<String> ComboEstado;
    private javax.swing.JComboBox<String> ComboEstadoCivil;
    private javax.swing.JComboBox<String> ComboSexo;
    private javax.swing.JFormattedTextField FormatCep;
    private javax.swing.JFormattedTextField FormatCpf;
    private javax.swing.JFormattedTextField FormatRg;
    private javax.swing.JFormattedTextField FormatTelefone;
    private javax.swing.JTextField JTxtNumero;
    private javax.swing.JLabel LblCep;
    private javax.swing.JLabel LblCidade;
    private javax.swing.JLabel LblCpf;
    private javax.swing.JLabel LblEmail;
    private javax.swing.JLabel LblEndereco;
    private javax.swing.JLabel LblEstado;
    private javax.swing.JLabel LblNome;
    private javax.swing.JLabel LblNumero;
    private javax.swing.JLabel LblPesquisaCli;
    private javax.swing.JLabel LblRg;
    private javax.swing.JLabel LblSexo;
    private javax.swing.JLabel LblTelefone;
    private javax.swing.JTable TblClientes;
    private javax.swing.JTextField TxtCidade;
    private javax.swing.JTextField TxtEmail;
    private javax.swing.JTextField TxtEndereco;
    private javax.swing.JTextField TxtNome;
    private javax.swing.JTextField TxtNumero;
    private javax.swing.JTextField TxtPesquisaCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
