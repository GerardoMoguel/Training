   M S B A M L   `   `   `  ����P  LInventarioRestaurante, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null=#clr-namespace:InventarioRestauranteInventarioRestaurante  5   	   0    *InventarioRestaurante.ModificarIngrediente   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   5local#clr-namespace:InventarioRestaurante     5   	     � Title$  ModificarIngrediente��5   	   $��486.529��6&   $��373.35��67   ���	#FF4E4E4E.��5	      �  � RowDefinitions 5
   
   �� 5      .+�5   
   �� -   5         ��Name$ lbNombreIngre��5      $��Nombre��6'   $
��Left=�68   $��
61,187,0,0q�6S   $	��Top=�6g   ���	#FFDADADA$��14(�6�   �� 5   
   -   5        $ lbCantIngre��5      $��Cantidad��6%   $
��Left=�68   $��
61,233,0,0q�6S   $	��Top=�6g   ���	#FFDADADA$��14(�6�   �� 5   
   -   5         ��Name$ 	txtNombre��5      $
��Left=�6%   $��23��6@   $��196,191,0,0q�6L    �� TextWrapping$
 Wrap=�6a   $	��Top=�6u   $	��120��6�   $��14(�6�    �� TextAlignment$ Center=�6�   ���	#FF383838���	#FFBCBCBC �� SelectionBrush �	#FFFFB3D96�   �� 5   
   -   5        $ txtCantidad��5      $
��Left=�6'   $��23��6B   $��196,237,0,0q�6N   $
 Wrap=�6c   $	��Top=�6w   $	��120��6�   $��14(�6�   $ Center=�6�   ���	#FF383838���	#FFBCBCBC �	#FFFFB3D96�   �� 5   
   -   5         ��Name$ btModificar��5      $��	Modificar��6&   $
��Left=�6:   $��215,378,0,0q�6U   $	��Top=�6j   $��80��6�   ���	#FFFFB3D9$��30��6�   $��14(�6�   $��Segoe UI Semibold)�6�   �� 5   
   -   5        $ lbOrigen��5      $��Descripcion��6"   $
��Left=�68   $��
61,278,0,0q�6S   $	��Top=�6g   ���	#FFDADADA$��14(�6�   $��27��6�   �� 5   
   -   5        $
 lbID��5      $��ID Ingrediente��6   $
��Left=�67   $��
61,140,0,0q�6R   $	��Top=�6f    J� RenderTransformOrigin$ 0.69,-1.388&�6~   
�������$��14(�6�   ��6�   Z� 6�   �� 5   
   -   5        $ lbPrecio��5      $��Precio��6"   $
��Left=�63   $��
61,324,0,0q�6N   $	��Top=�6b   ���	#FFDADADA$��14(�6�   �� 5   
   -	   5        $ txtDescripcion��5      $
��Left=�6*   $��23��6E   $��196,282,0,0q�6Q   $
 Wrap=�6f   $	��Top=�6z   $	��120��6�   ���	#FF383838���	#FFBCBCBC �	#FFFFB3D9$ Center=�6�   �� 5   
   -
   5        $ txtID��5      $
��Left=�6!   $��23��6<   $��196,144,0,0q�6H   $
 Wrap=�6]   $	��Top=�6q   $	��120��6�   $��14(�6�   $ Center=�6�   ���	#FF4F4F4F���	#FFBCBCBC �	#FFFFB3D9
�������6  �� 5   
   -   5        $ 	txtPrecio��5      $
��Left=�6%   $��23��6@   $��196,328,0,0q�6L   $
 Wrap=�6a   $	��Top=�6u   $	��120��6�   $��14(�6�   $ Center=�6�   ���	#FF383838���	#FFBCBCBC �	#FFFFB3D96�   �� 5   
   -   5        $ 	btn_atras��5      $��Atras��6$   $
��Left=�64   $��
10,421,0,0q�6O   $	��Top=�6c   $��66��6{   $��25��6�   ���	#FFD1B3FF6�   �� 5   
   -   5        $ label1��5      $����         Por favor, introduzca el identificador único 
del ingrediente y proporcione exclusivamente los datos
 correspondientes a los atributos que desee modificar.��6    $��37,53,42.2,0q�6�   $	��Top=�6�   $��76��6  $��Visible=�6  $��Stretch=�6F  $��Center=�6k  $��Segoe UI Light)�6�  $ 0.5,0.5&�6�  4  .System.Windows.Automation.AutomationProperties	   IsColumnHeader	 . 
   IsOffscreenBehavior$
 Onscreen=�6
     IsRequiredForForm . ���	#FFDADADA��6�  Z� 6l  �� 5   
   -   5        $ lbId_Restaurante_Copy��5      $��Modificar ingrediente.��6/   $
��Left=�6P   $	��Top=�6k   $��16(�6�   $��43��6�   ���	#FFDADADA$��
100,16,0,0q�6�   $��Segoe UI Semibold)�6�   5      5      