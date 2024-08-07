PGDMP  5    *                |            MotorPH    16.3    16.3 $    
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16475    MotorPH    DATABASE     �   CREATE DATABASE "MotorPH" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "MotorPH";
                postgres    false            �            1259    24577 
   attendance    TABLE     �   CREATE TABLE public.attendance (
    id integer NOT NULL,
    employee_id integer NOT NULL,
    time_in timestamp without time zone,
    time_out timestamp without time zone
);
    DROP TABLE public.attendance;
       public         heap    postgres    false            �            1259    24576    attendance_id_seq    SEQUENCE     �   CREATE SEQUENCE public.attendance_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.attendance_id_seq;
       public          postgres    false    220                       0    0    attendance_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.attendance_id_seq OWNED BY public.attendance.id;
          public          postgres    false    219            �            1259    16495 	   employees    TABLE     �  CREATE TABLE public.employees (
    id integer NOT NULL,
    lastname character varying(100),
    firstname character varying(100),
    birthday date,
    address text,
    phonenumber character varying(20),
    sssnumber character varying(20),
    philhealthnumber character varying(20),
    tinnumber character varying(20),
    pagibignumber character varying(20),
    status character varying(50),
    "position" character varying(100),
    immediatesupervisor character varying(100),
    basicsalary numeric(10,2),
    ricesubsidy numeric(10,2),
    phoneallowance numeric(10,2),
    clothingallowance numeric(10,2),
    grosssemimonthlyrate numeric(10,2),
    hourlyrate numeric(10,2)
);
    DROP TABLE public.employees;
       public         heap    postgres    false            �            1259    16494    employees_id_seq    SEQUENCE     �   CREATE SEQUENCE public.employees_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.employees_id_seq;
       public          postgres    false    216                       0    0    employees_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.employees_id_seq OWNED BY public.employees.id;
          public          postgres    false    215            �            1259    24589    leave_requests    TABLE     	  CREATE TABLE public.leave_requests (
    id integer NOT NULL,
    employee_id integer NOT NULL,
    leave_start_date date NOT NULL,
    leave_end_date date NOT NULL,
    reason text NOT NULL,
    status character varying(20) DEFAULT 'Pending'::character varying
);
 "   DROP TABLE public.leave_requests;
       public         heap    postgres    false            �            1259    24588    leave_requests_id_seq    SEQUENCE     �   CREATE SEQUENCE public.leave_requests_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.leave_requests_id_seq;
       public          postgres    false    222                       0    0    leave_requests_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.leave_requests_id_seq OWNED BY public.leave_requests.id;
          public          postgres    false    221            �            1259    16504    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    employee_id integer NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16503    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    218                       0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    217            a           2604    24580    attendance id    DEFAULT     n   ALTER TABLE ONLY public.attendance ALTER COLUMN id SET DEFAULT nextval('public.attendance_id_seq'::regclass);
 <   ALTER TABLE public.attendance ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            _           2604    16498    employees id    DEFAULT     l   ALTER TABLE ONLY public.employees ALTER COLUMN id SET DEFAULT nextval('public.employees_id_seq'::regclass);
 ;   ALTER TABLE public.employees ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            b           2604    24592    leave_requests id    DEFAULT     v   ALTER TABLE ONLY public.leave_requests ALTER COLUMN id SET DEFAULT nextval('public.leave_requests_id_seq'::regclass);
 @   ALTER TABLE public.leave_requests ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222            `           2604    16507    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218                      0    24577 
   attendance 
   TABLE DATA           H   COPY public.attendance (id, employee_id, time_in, time_out) FROM stdin;
    public          postgres    false    220   -+                 0    16495 	   employees 
   TABLE DATA             COPY public.employees (id, lastname, firstname, birthday, address, phonenumber, sssnumber, philhealthnumber, tinnumber, pagibignumber, status, "position", immediatesupervisor, basicsalary, ricesubsidy, phoneallowance, clothingallowance, grosssemimonthlyrate, hourlyrate) FROM stdin;
    public          postgres    false    216   �+                 0    24589    leave_requests 
   TABLE DATA           k   COPY public.leave_requests (id, employee_id, leave_start_date, leave_end_date, reason, status) FROM stdin;
    public          postgres    false    222   �9                 0    16504    users 
   TABLE DATA           D   COPY public.users (id, username, password, employee_id) FROM stdin;
    public          postgres    false    218   h:                  0    0    attendance_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.attendance_id_seq', 2, true);
          public          postgres    false    219                       0    0    employees_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.employees_id_seq', 1, false);
          public          postgres    false    215                       0    0    leave_requests_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.leave_requests_id_seq', 4, true);
          public          postgres    false    221                       0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 36, true);
          public          postgres    false    217            k           2606    24582    attendance attendance_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT attendance_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.attendance DROP CONSTRAINT attendance_pkey;
       public            postgres    false    220            e           2606    16502    employees employees_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.employees DROP CONSTRAINT employees_pkey;
       public            postgres    false    216            m           2606    24597 "   leave_requests leave_requests_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.leave_requests
    ADD CONSTRAINT leave_requests_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.leave_requests DROP CONSTRAINT leave_requests_pkey;
       public            postgres    false    222            g           2606    16511    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    218            i           2606    16513    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public            postgres    false    218            o           2606    24583 &   attendance attendance_employee_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.attendance
    ADD CONSTRAINT attendance_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES public.employees(id);
 P   ALTER TABLE ONLY public.attendance DROP CONSTRAINT attendance_employee_id_fkey;
       public          postgres    false    216    4709    220            p           2606    24598 .   leave_requests leave_requests_employee_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.leave_requests
    ADD CONSTRAINT leave_requests_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES public.employees(id);
 X   ALTER TABLE ONLY public.leave_requests DROP CONSTRAINT leave_requests_employee_id_fkey;
       public          postgres    false    216    222    4709            n           2606    16514    users users_employee_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES public.employees(id);
 F   ALTER TABLE ONLY public.users DROP CONSTRAINT users_employee_id_fkey;
       public          postgres    false    4709    218    216               H   x�]˱�0���F��3���#J�}�3�IP��:��lu��۫�b{3��?��M���l�ha�t5         B  x��Z�r��}>��3ә������H�7�R�Z�3���<�P��
�N��� HQ��Xv\N$1�@p���k�c�^�vQ&v��M����	�1�B�BJ�>U�m�qjoS{Ï6e�,��^��ۜ{.���,ݤ���eǢsEp�P^0c
c���%�r��h%~�
�^8�J�\�*Z�E�eo�jS��_���?�5/6}�!����En��sq���Jiǟj���O�x|b��yy��i�f�o�!�����]��O/�g����&qŏ�fq�%?mzx��x�Y�֩-��!,�>3�%��� `VJ8��)"�Zzm�qZ憠��{7�u:
������6��%�E;&��%��v��m�L����ݔuÎ�T/�h��v� a0��zƍP������g�]�nV|�!����ig������/I�s��}�������S/�*o��`YDܼ��
b��he�^GE��]/� �w�c']��UE��f(ǌ�O���m���}�֛��8�#ݺ�E>P���x�
��I[�.FF)�[e�y�0z�t#]4^�GH��g��νe�r�.�G���.���Bi�j��S�_���_���7��Y٥u���r��>UL�e��ژYQX�r֡�,Zo����̢�5ڀ F��o��zSwCs]�u}^wԤ�nDC9� �|�U���Uꙕ��/�N�CB�/�L��S��B�a��ش���=�{�A#sa?o.���G����ύ�?��UB!tT��)	��DT�VB�֙
n����C��Q�3o��Z�V�E��[�nZ!���<���2;j�8�k@;�Hx�������p�]U���F�w闲�o�G*Uԇ�Ca!�4H��QF��:2�J�.�-��a���>�w9��iNK@�O�K�Q��}���%M�kgA��YoRE=��'!�C~��Pk��-�H��|~ۃܔ�o�_��AR�j���\��h��lN�ρ|�������.k,�9P�V,��,��h&�R����m�o��~QVy�>�G)���� ��h=�� �y_��c��$�&l0������u��� �"�MO�W6��a�}x�X9�WCk;cb��M�����`��(|�#b'� Y+m�wB,r�!��r����źЂ�E����Yi��i���E���!�
L=q��h�/ǜ)<"",$
�>���qE�!��f�� � ������~�Ҟ�+0_����Z�eC:庩免5%���E�^�-�I�TU��2�B��w�%.Q�h��2!�� u�ݰ@sw@�!�1*[R�bJ�sk�i4�2^��������a�	�Y��,`=)5�H2DYs�6~=Uu �Hk�Y�PZ�n��:ݣ�wi�m�u�ހ�̪�*�]h�_*jS�1ـ�E�,�3 ��O�]�S`�O@�'�)G|�#�����G�Rq&ԁ��EjSE����I|@l��:T�N	��*w��cY*�����I�*�5�V�Rm
� ]l�d:�I�(�4:D�,������4�Uxiom�>��hi|��wzĨYp�%�,kd�4���BE
Op�?4����;TQZv$�ҪAk.�Sh߲%�<]�J6�##�4�Э(v�U|����(UX9��Y�G��}`ڽ�����-c�aS��V���e�K��g���R�D�J�C��:�9��DjZh�@z
��ic1�"y@vS�*�@xታ1էU�~�ɿe(?���af���Y��j[v=�n�Y$FA���&竌�ݴc�j��ru���ʃ�.6��Yv�jv�R��'�M42�9����AkN
u+�<��1��{t��K�U��n�475��U��µ�"��s>o�dy�a�7��"���$Q��ѕFC��]���!m�
�@󁙀7��
�W�<�"|z��!Z��ZG�V&�$FO�׏�,e��ud� 2@>/[L��%#>Jfغ=N>���jFy}젡�O(2�2 n%��0J�>�A��E��(�A���Mtl(H���w�$�}I>UiDU��;�[��n�Ψi�0t�J�ܮ2��u�a�ܦ��&�.�� �1V?�"2G�ld�B�����X^�;��OE��=J�3�x)��)�,?۴��w�C�D�h�����9������(��Z	qMj��t$�?�R^D��lA��S���~������(�����Ԣ��ƴs�P�C <E0���+��$�@W���S�4�������D�jmG���X
�a%���7�lr�F.R��a�a���rt����A��^�&]ر7MuI�M�T�c����M���)��_�\w�?��K��J��$�eоZ��F �_����~V�`^࣡8�N\!�!8��Ӧ��l��~Z���C
�}���K�Ia���,a�����<ByԓY"���	��X�U^0�B��%�A�Q��jԻ
H���k�?����o�l�4���1?4ccI~�m�\��u��o���ގ�&�濛Ӌ֦r�[$Rk��G����-FˬCk@g������v��b�zO]�:-n��a��a7Yt���w%Lڋ
O&�s=(���ޑ�~r�Ya�;����&���<�a��Lu��5,��$�j�����P��Ie������S�ޱ���ܶ��]�R5��1�6���%�B�F�N��~���+��A��5U>��1��}P����>Т�i(�Q�����p^��Aq����������挟iu �G
s�Kw�7�*s��4�9�����.�H��4��1�J���rs�����v~h$�F�t�@��0#*8�iF��A֚���$:�=���xZ°a˫D�ԏ��� �!�O���YM]��e��8ݔ�,�]��.9��%.���n�&2��B��Y�,�5�h0�b�"��Ɗ�vKk��1}52em�A`}Z�7����0��MbRtES
����>CPN��A��#��'/�M� Cv#_�i��
�4���\�6�T�ЖĂ΃�G��G4�T��X��$C���aغ>_��I��A[:���^(i�����Z"�] �-�{MnV~�R����Fܒ�a�H>�pө�����m^C�����Ԣh�t�Up�M�Q��="�d���SD�Fm����r#�iC�Ԋ2	���@�P�d%����[_�\M;m�٫������)��}�/7C�H(�xI�4��	�q�OF��Zu�B�8��wû�o�>^�ځ���j<!��'/3!w�t'�ӓa_����w�Y>NkR%�l�8�$�'HHX)p�g�:;�]�ʴhR0���W�{SE�o�묋��m[s�P'���fE�b�};6�՗G9�m�q�Eg�U�nKȘ��YFn����W��I&&@�Lɐ7ˢ�	;���z�à�����АmM�-��E@���x��� -�I�o0��\���n:(���?yu��_ ���s���D� t/���k���п��b�{�'�D���'Awg��w*���h����[#4h�<Ⱋ�d>Nu���]�3��@��ŏڍ*v|�1�ރ�ۯ�����kT@ߴ�`�]x����=;88�KӋ�         �   x�E�;� ��ٜ��*64a�:gɃ֤�@U��u ɲ����!���%5��d�����2�df�dbf�c���-��ycu5-L~ϼ�G�t[V��:2����[ݜ/m��h�;m�w!�	c�1v         �  x�m�ۮ!E�폩�f���g�3%�K
�T��דJ�@yA�K����@����&���a��:���'�� ��.TE�.���b��p�>m��=�`�%n�U�'���Tl&�Ar
w�mόd9���Z��)�m��!H�ڭA��p����B�d |��Xb�z�A��b��#��Y8H#�A���i��YoS�Ʉ4��ՠ�H�9ɮAr�"�Õ�&�	6/��"&d�oՐ�^w�z�̨��1�=��I��\��m<ڸ��<�mW�6��,�DN�����ȳ���ἷ�4�f��	��M6�����th����K��� �*�h,3�Ɯ���c���d�~�� �*�[����hF��:6���E�ܽو? �_�'I     