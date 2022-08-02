package br.com.servicos.forms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.servicos.dao.ModuloConexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaOS extends javax.swing.JInternalFrame {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = null;

    private String tipo;

    public TelaOS() {
        initComponents();
    }

    private void LimpaCampos() {
        TxtOs.setText(null);
        TxtData.setText(null);
        TxtNome.setText(null);
        TxtAreaDescServico.setText(null);
        TxtValorTotal.setText(null);
        TxtIdCli.setText(null);
        ((DefaultTableModel) TblClientes.getModel()).setRowCount(0);
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
            JOptionPane.showMessageDialog(null, "Não foi possivel encerrar o Statement!" + ex.getMessage());
        }
        try {
            conexao.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel fechar a conexão com o banco de dados!" + ex.getMessage());
        }
    }

    private void SetarCampos() {
        int setar = TblClientes.getSelectedRow();
        TxtIdCli.setText(TblClientes.getModel().getValueAt(setar, 0).toString());
    }

    private void IncluirOs() {
        conexao = ModuloConexao.conector();
        String sql = "insert into tbl_ordemservicos(tipo_os,situacao_os,servico_os,valor_os,id_cli)values(?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, ComboSituacao.getSelectedItem().toString());
            pst.setString(3, TxtAreaDescServico.getText());
            pst.setString(4, TxtValorTotal.getText().replace(",", "."));
            pst.setString(5, TxtIdCli.getText());
            if (TxtAreaDescServico.getText().isEmpty() || (TxtIdCli.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "preencha todos os campos obrigatórios!");
            } else {
                int salvo = pst.executeUpdate();
                if (salvo > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço emitida com sucesso!");
                    LimpaCampos();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar a ordem de serviço!" + ex.getMessage());
        }

    }

    private void ConsultaOs() {
        conexao = ModuloConexao.conector();
        String numero_os = JOptionPane.showInputDialog("Número da Ordem de serviço");
        String sql = "select * from tbl_ordemservicos where os= " + numero_os;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                TxtOs.setText(rs.getString(1));
                TxtData.setText(rs.getString(2));
                String radiotipo = rs.getString(3);
                if (radiotipo.equals("Ordem de Serviço")) {
                    RadioOrdemServico.setSelected(true);
                    tipo = "Ordem de Seviço";
                } else {
                    RadioOrcamento.setSelected(true);
                    tipo = "Orçamento";
                }
                ComboSituacao.setSelectedItem(rs.getString(4));
                TxtAreaDescServico.setText(rs.getString(5));
                TxtValorTotal.setText(rs.getString(6));
                TxtIdCli.setText(rs.getString(7));
                BtnSalvar.setEnabled(false);
                TxtNome.setEnabled(false);
                TblClientes.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Ordem de serviço não cadastrada!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel consultar a ordem de serviço!" + ex.getMessage());
        }
    }

    private void EditarOs() {
        conexao = ModuloConexao.conector();
        String sql = "update tbl_ordemservicos set tipo_os=?,situacao_os=?,servico_os=?,valor_os=? where os=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, ComboSituacao.getSelectedItem().toString());
            pst.setString(3, TxtAreaDescServico.getText());
            pst.setString(4, TxtValorTotal.getText().replace(",", "."));
            pst.setString(5, TxtOs.getText());
            if (TxtAreaDescServico.getText().isEmpty() || (TxtIdCli.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "preencha todos os campos obrigatórios!");
            } else {
                int salvo = pst.executeUpdate();
                if (salvo > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço atualizada com sucesso!");
                    LimpaCampos();
                    BtnSalvar.setEnabled(true);
                    TxtNome.setEnabled(true);
                    TblClientes.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel atualizar a ordem de serviço!" + ex.getMessage());
        }
    }

    private void ExcluirOs() {
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a Exclusão da OS?", "Atenção!", JOptionPane.YES_NO_OPTION);
        conexao = ModuloConexao.conector();
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbl_ordemservicos where os=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, TxtOs.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço apagado com sucesso!");
                    LimpaCampos();
                    BtnSalvar.setEnabled(true);
                    TxtNome.setEnabled(true);
                    TblClientes.setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaOS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    private void ImprimirOs() {
        conexao = ModuloConexao.conector();
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão da Ordem de Serviço?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                HashMap filtro = new HashMap();
                filtro.put("os", Integer.parseInt(TxtOs.getText()));
                JasperPrint print = JasperFillManager.fillReport("C:\\Users\\Limitado - 3584\\Desktop\\NetBeansProjects\\Relatorios\\OS.jasper", filtro, conexao);
                JasperViewer.viewReport(print, false);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null, "Falha na impressão da Ordem de Seviço!" + ex.getMessage());
            }
        }
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
        TxtIdCli = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblClientes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtAreaDescServico = new javax.swing.JTextArea();
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
        TxtData.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N

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
                    .addComponent(TxtData, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
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

        TxtIdCli.setEnabled(false);

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
            TblClientes.getColumnModel().getColumn(1).setPreferredWidth(60);
            TblClientes.getColumnModel().getColumn(2).setPreferredWidth(90);
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
                        .addComponent(TxtIdCli))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LblSearch))
                    .addComponent(LblId)
                    .addComponent(TxtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        TxtAreaDescServico.setColumns(20);
        TxtAreaDescServico.setRows(5);
        jScrollPane2.setViewportView(TxtAreaDescServico);

        LblDescricaoServico.setText("* Descrição do serviço");

        LblValorTotal.setText("Valor Total R$");

        TxtValorTotal.setText("0");

        BtnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/save.png"))); // NOI18N
        BtnSalvar.setToolTipText("Salvar");
        BtnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarActionPerformed(evt);
            }
        });

        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/edit.png"))); // NOI18N
        BtnEditar.setToolTipText("Editar");
        BtnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });

        BtnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/delete.png"))); // NOI18N
        BtnDeletar.setToolTipText("Deletar");
        BtnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeletarActionPerformed(evt);
            }
        });

        BtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/search_3.png"))); // NOI18N
        BtnBuscar.setToolTipText("Consultar");
        BtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/servicos/icones/print.png"))); // NOI18N
        jButton5.setToolTipText("Imprimir OS");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(BtnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(BtnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDeletar)
                    .addComponent(BtnBuscar)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnSalvar))
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
        tipo = "Orçamento";
    }//GEN-LAST:event_RadioOrcamentoActionPerformed

    private void RadioOrdemServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioOrdemServicoActionPerformed
        tipo = "Ordem de Serviço";
    }//GEN-LAST:event_RadioOrdemServicoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        RadioOrcamento.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void BtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarActionPerformed
        IncluirOs();
    }//GEN-LAST:event_BtnSalvarActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        ConsultaOs();
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        EditarOs();
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeletarActionPerformed
        ExcluirOs();
    }//GEN-LAST:event_BtnDeletarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ImprimirOs();
    }//GEN-LAST:event_jButton5ActionPerformed


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
    private javax.swing.JTextArea TxtAreaDescServico;
    private javax.swing.JTextField TxtData;
    private javax.swing.JTextField TxtIdCli;
    private javax.swing.JTextField TxtNome;
    private javax.swing.JTextField TxtOs;
    private javax.swing.JTextField TxtValorTotal;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
