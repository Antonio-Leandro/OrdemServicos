package br.com.servicos.forms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.servicos.dao.ModuloConexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaOS extends javax.swing.JInternalFrame {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = null;
    
    private String tipo;

    public TelaOS() {
        initComponents();
    }

    private void PesquisaCliente() {
        conexao = ModuloConexao.conector();
        String sql = "select id_cli as Id, nomecli as Nome, fonecli as Telefone from tbl_clientes where nomecli like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, TxtNome.getText() + "%");
            rs = pst.executeQuery();
            TblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a consulta!" + ex.getMessage());
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel encerrar o ResultSet!" + " " + ex.getMessage());
        }
        try {
            pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel encerrar o Statement!"+ ex.getMessage());
        }
        try {
            conexao.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel fechar a conexão com o banco de dados!"+ ex.getMessage());
        }
    }
    
    private void SetarCampos(){
        int setar = TblClientes.getSelectedRow();
        TxtId.setText(TblClientes.getModel().getValueAt(setar, 0).toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        LblOs = new javax.swing.JLabel();
        LblData = new javax.swing.JLabel();
        TxtOs = new javax.swing.JTextField();
        TxtData = new javax.swing.JTextField();
        RadioOrcamento = new javax.swing.JRadioButton();
        RadioOrdemServico = new javax.swing.JRadioButton();
        LblSituacao = new javax.swing.JLabel();
        ComboSituacao = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        TxtNome = new javax.swing.JTextField();
        LblSearch = new javax.swing.JLabel();
        LblId = new javax.swing.JLabel();
        TxtId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblClientes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        LblDescricaoServico = new javax.swing.JLabel();
        LblValorTotal = new javax.swing.JLabel();
        TxtValorTotal = new javax.swing.JTextField();
        BtnSalvar = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnDeletar = new javax.swing.JButton();
        BtnBuscar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        setPreferredSize(new java.awt.Dimension(640, 480));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LblOs.setText("Nº OS");

        LblData.setText("Data");

        TxtOs.setEditable(false);

        TxtData.setEditable(false);

        buttonGroup1.add(RadioOrcamento);
        RadioOrcamento.setText("Orçamento");
        RadioOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioOrcamentoActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioOrdemServico);
        RadioOrdemServico.setText("Ordem de Serviço");
        RadioOrdemServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioOrdemServicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblOs)
                    .addComponent(RadioOrcamento)
                    .addComponent(TxtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RadioOrdemServico)
                    .addComponent(LblData)
                    .addComponent(TxtData, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblOs)
                    .addComponent(LblData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RadioOrcamento)
                    .addComponent(RadioOrdemServico))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        LblSituacao.setText("Situação");

        ComboSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em andamento", "Concluído", "Aguardando aprovação" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        TxtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtNomeKeyReleased(evt);
            }
        });

        LblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/search_1.png"))); // NOI18N

        LblId.setText("* Id");

        TxtId.setEnabled(false);

        TblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Fone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblClientes.setFocusable(false);
        TblClientes.getTableHeader().setResizingAllowed(false);
        TblClientes.getTableHeader().setReorderingAllowed(false);
        TblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblClientes);
        if (TblClientes.getColumnModel().getColumnCount() > 0) {
            TblClientes.getColumnModel().getColumn(0).setPreferredWidth(10);
            TblClientes.getColumnModel().getColumn(1).setResizable(false);
            TblClientes.getColumnModel().getColumn(1).setPreferredWidth(90);
            TblClientes.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtId))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblSearch))
                    .addComponent(LblId)
                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        LblDescricaoServico.setText("* Descrição do serviço");

        LblValorTotal.setText("Valor Total");

        BtnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/save.png"))); // NOI18N
        BtnSalvar.setToolTipText("Salvar");
        BtnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/edit.png"))); // NOI18N
        BtnEditar.setToolTipText("Editar");
        BtnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        BtnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/delete.png"))); // NOI18N
        BtnDeletar.setToolTipText("Deletar");
        BtnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        BtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/search_3.png"))); // NOI18N
        BtnBuscar.setToolTipText("Consultar");
        BtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/print.png"))); // NOI18N
        jButton5.setToolTipText("Imprimir OS");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LblSituacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboSituacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblDescricaoServico)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LblValorTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(BtnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblSituacao)
                            .addComponent(ComboSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addComponent(LblDescricaoServico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblValorTotal)
                    .addComponent(TxtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSalvar)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDeletar)
                    .addComponent(BtnBuscar)
                    .addComponent(jButton5))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        setBounds(0, 0, 555, 478);
    }// </editor-fold>//GEN-END:initComponents

    private void TxtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNomeKeyReleased
        PesquisaCliente();
    }//GEN-LAST:event_TxtNomeKeyReleased

    private void TblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblClientesMouseClicked
        SetarCampos();
    }//GEN-LAST:event_TblClientesMouseClicked

    private void RadioOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioOrcamentoActionPerformed
        tipo ="Orçamento";
    }//GEN-LAST:event_RadioOrcamentoActionPerformed

    private void RadioOrdemServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioOrdemServicoActionPerformed
        tipo ="Ordem de Serviço";
    }//GEN-LAST:event_RadioOrdemServicoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        RadioOrcamento.setSelected(true);
        tipo ="Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnDeletar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnSalvar;
    private javax.swing.JComboBox<String> ComboSituacao;
    private javax.swing.JLabel LblData;
    private javax.swing.JLabel LblDescricaoServico;
    private javax.swing.JLabel LblId;
    private javax.swing.JLabel LblOs;
    private javax.swing.JLabel LblSearch;
    private javax.swing.JLabel LblSituacao;
    private javax.swing.JLabel LblValorTotal;
    private javax.swing.JRadioButton RadioOrcamento;
    private javax.swing.JRadioButton RadioOrdemServico;
    private javax.swing.JTable TblClientes;
    private javax.swing.JTextField TxtData;
    private javax.swing.JTextField TxtId;
    private javax.swing.JTextField TxtNome;
    private javax.swing.JTextField TxtOs;
    private javax.swing.JTextField TxtValorTotal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
