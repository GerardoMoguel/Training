   M S B A M L   `   `   `  ����P  LInventarioRestaurante, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null=#clr-namespace:InventarioRestauranteInventarioRestaurante  5   	   "    InventarioRestaurante.Cuenta   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   5local#clr-namespace:InventarioRestaurante     5   	   -   5          �Name$  Cuentas��5   	    � Title$ Cuenta��5   	   $��495.6��6   $��645.6��6'   ���	#FF4E4E4E.��5	      �  � RowDefinitions 5
   
   �� 5       � ColumnDefinitions 5   
   �� 5      �� 5      $��0* �6   .+�5   
   �� -   5         ��Name$ cbPlatillos��5      $
��Left=�6(   $��
39,119,0,0q�6C   $	��Top=�6W   $	��234��6o   $��26��6{   ���	#FF383838���	#FF3A3A3A$��14(�6�   �� 5   
   -   5         ��Name$ btnAgregaPlatillo��5      $��Agregar platillo��6,   $
��Left=�6G   $��
157,83,0,0q�6b   $	��Top=�6v   $	��116��6�   $��27��6�   ���	#FFD2FFD3$��14(�6�   $��Segoe UI Semibold)�6�   �� 5   
   -   5        $ btnFinalizaCuenta��5      $��Finalizar cuenta��6,   $
��Left=�6G   $��397,400,0,0q�6b   $	��Top=�6w   $	��124��6�   $��33��6�   ���	#FFD2FFEB$��14(�6�   $��Segoe UI Semibold)�6�   �� 5   
   -   5        $ 	btn_atras��5      $��Atras��6$   $
��Left=�64   $��
10,431,0,0q�6O   $	��Top=�6c   $��66��6{   $��25��6�   $��Segoe UI Light)�6�   ���	#FFD1B3FF6�   &   System.Windows.Controls.DataGrid  5   
   -   5          Name$ 
dgCuentaRT��5      $
��Left=�6'   $	��222��6B   $��316,114,0,0q�6O   $	��Top=�6d   $	��284��6|   ���	#FF383838���	#FF3A3A3A���	#FF707070$��14(�6�      Columns 5      0  *System.Windows.Controls.DataGridTextColumn  5      ,  &System.Windows.Controls.DataGridColumn   Header$ Nombre del Platillo��6%   1  +System.Windows.Controls.DataGridBoundColumn	   Binding	 6B   �� 5      �� 5   
   -   5        
 ��Name$
 lblPlatillo��5      $��	Platillos��6%   $
��Left=�69   $��	39,88,0,0q�6T   $	��Top=�6g   $��26��6   $��58��6�   $��14(�6�   ���	#FFDADADA6�   �� 5   
   -   5        $
 lblVenta��5      $��Venta.��6"   $
��Left=�63   $	��Top=�6N   $��18(�6f   $��43��6t   ���	#FFDADADA$��
286,25,0,0q�6�   $��Segoe UI Semibold)�6�   $��59��6�   �� 5   
   $��Total��6   $
��Left=�6    $��359,356,0,0q�6;   $	��Top=�6P   $��26��6h   $��65��6t   $��14(�6   ���	#FFDADADA6�   �� 5   
   $��Cuenta��6   $
��Left=�6!   $��
316,88,0,0q�6<   $	��Top=�6P   $��26��6h   $��65��6t   $��14(�6   ���	#FFDADADA6�   �� 5   
   ���	#FF383838$
��Left=�6%   $��26��6@   $��439,356,0,0q�6L   ���	#FF707070$	��Top=�6t   $	��118��6�   �� 5   
   -	   5         ��Name$ 	txtBTotal��5      $
��Left=�6'   $��439,356,0,0q�6B   $
��Wrap=�6W   $	��Top=�6k   $��26��6�   $	��118��6�   ���	#FFBCBCBC$��14(�6�    �� TextAlignment$ Center=�6�   5!      5"      