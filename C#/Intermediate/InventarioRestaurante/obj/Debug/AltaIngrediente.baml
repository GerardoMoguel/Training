   M S B A M L   `   `   `  ����P  LInventarioRestaurante, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null=#clr-namespace:InventarioRestauranteInventarioRestaurante  5   	   +    %InventarioRestaurante.AltaIngrediente   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   5local#clr-namespace:InventarioRestaurante     5   	     � Title$  AltaIngrediente��5   	   $��398.2��6!   $��337.05��60   ���	#FF4E4E4E.��5	      � .+�5
   
   �� -   5         ��Name$ 	txtNombre��5
      $
��Left=�6%   $��29��6@   $��177,103,0,0q�6L    �� TextWrapping$
 Wrap=�6a   $�� ��6u   $	��Top=�6~   $	��120��6�   ���	#FF383838���	#FFBCBCBC �� SelectionBrush �	#FF00D9EE$��14(�6�    �� TextAlignment$ Center=�6�   �� 5   
   -   5        $ txtCantidad��5      $
��Left=�6'   $��30��6B   $��177,190,0,0q�6N   $
 Wrap=�6c   $�� ��6w   $	��Top=�6�   $	��120��6�   ���	#FF383838���	#FFBCBCBC �	#FF00D9EE$��14(�6�   $ Center=�6�   �� 5   
   -   5         ��Name$ btDarDeAlta��5      $
��Alta��6&   $
��Left=�65   $��196,287,0,0q�6P   $	��Top=�6e   $��85��6}   ���	#FFB3F5FF$��34��6�   $��14(�6�   $��Segoe UI Semibold)�6�   �� 5   
   -   5        $ txtDescripcion��5      $
��Left=�6*   $��29��6E   $��177,146,0,0q�6Q   $
 Wrap=�6f   $�� ��6z   $	��Top=�6�   $	��120��6�   ���	#FF383838���	#FFBCBCBC �	#FF00D9EE$��14(�6�   $ Center=�6�   �� 5   
   -   5        $ txtId_Restaurante��5      $
��Left=�6-   $��29��6H   $��
177,60,0,0q�6T   $
 Wrap=�6h   $	��Top=�6|   $	��120��6�   ���	#FF383838���	#FFBCBCBC �	#FF00D9EE$��14(�6�   $ Center=�6�   �� 5   
   -   5        $ 	txtPrecio��5      $
��Left=�6%   $��29��6@   $��177,238,0,0q�6L   $
 Wrap=�6a   $�� ��6u   $	��Top=�6~   $	��120��6�   ���	#FF383838���	#FFBCBCBC �	#FF00D9EE$��14(�6�   $ Center=�6�   �� 5   
   -   5        $ 	btn_atras��5      $��Atras��6$   $
��Left=�64   $��
10,334,0,0q�6O   $	��Top=�6c   $��66��6{   ���	#FFD1B3FF$��Segoe UI Light)�6�   $��25��6�   �� 5   
   $
��Left=�6   $	��248��60   $��	33,60,0,0q�6=   $	��Top=�6P   $	��139��6h   .+�5      �� -   5         ��Name$ lbId_Restaurante��5      $��Id_Restaurante��6.   $
��Left=�6G   $	��Top=�6b   $��14(�6z   $��43��6�   ���	#FFDADADA6�   �� 5      -	   5        $ lbNombreIngre��5      $��Nombre��6+   $
��Left=�6<   $	��Top=�6W   $��14(�6o   $��43��6}   ���	#FFDADADA6�   �� 5      -
   5        $ lbOrigen��5      $��Descripcion��6&   $
��Left=�6<   $	��Top=�6W   $��14(�6o   $��44��6}   ���	#FFDADADA6�   �� 5      -   5        $ lbCantIngre��5      $��Cantidad��6)   $
��Left=�6<   $	��Top=�6W   $��14(�6o   $��43��6}   ���	#FFDADADA6�   �� 5      -   5        $ lbPrecio��5      $��Precio��6&   $
��Left=�67   $	��Top=�6R   $��14(�6j   $��48��6x   ���	#FFDADADA6�   5      �� 5   
   -   5        $ lbId_Restaurante_Copy��5      $��Alta de ingrediente.��6/   $
��Left=�6N   $	��Top=�6i   $��16(�6�   $��43��6�   ���	#FFDADADA$��	84,12,0,0q�6�   $��Segoe UI Semibold)�6�   5      5      